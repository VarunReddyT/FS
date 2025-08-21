# Rate Limiting and Traffic Management

## 1. Token Bucket Algorithm Implementation
**Question:** In a Token Bucket system with a fill rate of 5 tokens/sec and a bucket size of 20, what happens if 50 requests arrive instantly at t=0?
A. All requests pass
B. 20 requests pass, 30 delayed/rejected
C. 25 requests pass, 25 delayed
D. All rejected
**Answer: B. 20 requests pass, 30 delayed/rejected**

### Detailed Explanation
#### Why B is correct:
1. **Initial State:**
   - Bucket is full with 20 tokens at t=0
   - Fill rate is 5 tokens/second

2. **Processing Flow:**
   - 50 requests arrive instantly
   - First 20 requests consume all available tokens
   - Remaining 30 requests can't be processed immediately

3. **Mathematical Analysis:**
```python
bucket_size = 20
fill_rate = 5  # tokens/second
initial_requests = 50

# Initial processing
immediate_processed = min(bucket_size, initial_requests)  # 20
remaining = initial_requests - immediate_processed  # 30
```

#### Implementation Example
```javascript
class TokenBucket {
    constructor(capacity, fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        this.tokens = capacity;
        this.lastFill = Date.now();
    }

    tryConsume() {
        this.refill();
        if (this.tokens > 0) {
            this.tokens--;
            return true;
        }
        return false;
    }

    refill() {
        const now = Date.now();
        const elapsed = (now - this.lastFill) / 1000;
        const newTokens = elapsed * this.fillRate;
        this.tokens = Math.min(this.capacity, this.tokens + newTokens);
        this.lastFill = now;
    }
}
```

#### Why other options are incorrect:
1. **All requests pass (A):**
   - Impossible because bucket can only hold 20 tokens
   - Violates rate limiting principle

2. **25 requests pass, 25 delayed (C):**
   - Initial capacity is only 20 tokens
   - No mechanism to process extra 5 requests immediately

3. **All rejected (D):**
   - Too restrictive
   - Token bucket allows immediate processing up to capacity

### Best Practices
1. **Choosing Parameters:**
```javascript
const rateLimit = {
    // For API endpoints
    standard: {
        capacity: 100,
        fillRate: 10  // 10 req/sec
    },
    // For critical operations
    restricted: {
        capacity: 20,
        fillRate: 2   // 2 req/sec
    }
};
```

2. **Distributed Implementation:**
```python
# Using Redis for distributed rate limiting
import redis

class DistributedTokenBucket:
    def __init__(self, redis_client, key, capacity, fill_rate):
        self.redis = redis_client
        self.key = key
        self.capacity = capacity
        self.fill_rate = fill_rate

    def try_consume(self):
        pipeline = self.redis.pipeline()
        pipeline.get(f"{self.key}:tokens")
        pipeline.get(f"{self.key}:last_fill")
        tokens, last_fill = pipeline.execute()
        
        # Refill logic with distributed locking
        # ...
```

## 2. Rate Limiting Algorithms Comparison
**Question:** Why is the Token Bucket preferred over the Leaky Bucket for API rate limiting?
A. Handles only uniform traffic
B. Supports traffic bursts up to bucket capacity
C. Consumes fewer resources
D. Ensures zero packet loss
**Answer: B. Supports traffic bursts up to bucket capacity**

### Detailed Explanation
#### Why B is correct:
1. **Burst Handling:**
   - Allows accumulation of tokens
   - Can handle sudden traffic spikes
   - Better user experience for bursty traffic

2. **Implementation Example:**
```python
class TokenBucket:
    def __init__(self, capacity, fill_rate):
        self.capacity = capacity  # Max burst size
        self.fill_rate = fill_rate
        self.tokens = capacity   # Start full
        self.last_fill = time.time()

    def allow_request(self):
        now = time.time()
        time_passed = now - self.last_fill
        new_tokens = time_passed * self.fill_rate
        self.tokens = min(self.capacity, self.tokens + new_tokens)
        self.last_fill = now

        if self.tokens >= 1:
            self.tokens -= 1
            return True
        return False
```

#### Why other options are incorrect:
1. **Handles only uniform traffic (A):**
   - Actually describes Leaky Bucket
   - Token Bucket is better for non-uniform traffic

2. **Consumes fewer resources (C):**
   - Not a distinguishing factor
   - Both algorithms have similar resource usage

3. **Ensures zero packet loss (D):**
   - Neither algorithm guarantees this
   - Both can reject requests when overwhelmed

### Best Practices
1. **API Gateway Implementation:**
```javascript
// AWS API Gateway example
const api = new aws.apigateway.RestApi("myApi", {
    body: {
        swagger: "2.0",
        info: { title: "rate-limited-api", version: "1.0" },
        paths: {
            "/resource": {
                get: {
                    "x-amazon-apigateway-integration": {
                        type: "AWS_PROXY",
                        uri: lambda.invokeArn
                    },
                    responses: { "200": { description: "OK" } }
                }
            }
        }
    }
});

const usage_plan = new aws.apigateway.UsagePlan("myUsagePlan", {
    apiStages: [{
        apiId: api.id,
        stage: api.stageName
    }],
    throttleSettings: {
        burstLimit: 20,    // Token bucket capacity
        rateLimit: 10      // Fill rate
    }
});
```

## 3. Rate Limiting Challenges
**Question:** Which of the following is a direct drawback of Token Bucket under high sustained load?
A. Token starvation leading to request throttling
B. Over-acceptance of requests
C. Memory overhead due to token storage
D. Unfairness between clients
**Answer: A. Token starvation leading to request throttling**

### Detailed Explanation
#### Why A is correct:
1. **Token Starvation:**
   - Under sustained high load:
     - Tokens are consumed faster than replenishment
     - No tokens available for new requests
     - Leads to request throttling

2. **Monitoring Example:**
```python
class MonitoredTokenBucket:
    def __init__(self, capacity, fill_rate):
        self.bucket = TokenBucket(capacity, fill_rate)
        self.starvation_count = 0
        self.total_requests = 0

    def track_metrics(self):
        return {
            "starvation_rate": self.starvation_count / self.total_requests,
            "current_tokens": self.bucket.tokens,
            "capacity": self.bucket.capacity
        }
```

#### Why other options are incorrect:
1. **Over-acceptance of requests (B):**
   - Token Bucket strictly enforces limits
   - Cannot accept more than available tokens

2. **Memory overhead (C):**
   - Minimal memory footprint
   - Only stores token count and timestamp

3. **Unfairness between clients (D):**
   - Not inherent to Token Bucket
   - Can be addressed with per-client buckets

### Mitigation Strategies
1. **Adaptive Rate Limiting:**
```python
class AdaptiveTokenBucket:
    def __init__(self, initial_capacity, min_rate, max_rate):
        self.capacity = initial_capacity
        self.current_rate = max_rate
        self.min_rate = min_rate
        self.max_rate = max_rate

    def adjust_rate(self, load_factor):
        # Decrease rate under high load
        if load_factor > 0.8:
            self.current_rate = max(
                self.min_rate,
                self.current_rate * 0.8
            )
        # Increase rate under low load
        elif load_factor < 0.2:
            self.current_rate = min(
                self.max_rate,
                self.current_rate * 1.2
            )
```

2. **Distributed Rate Limiting:**
```python
class DistributedTokenBucket:
    def __init__(self, redis_client):
        self.redis = redis_client

    def acquire_token(self, key):
        script = """
        local current = tonumber(redis.call('get', KEYS[1]) or 0)
        local capacity = tonumber(ARGV[1])
        local now = tonumber(ARGV[2])
        local rate = tonumber(ARGV[3])
        local last_update = tonumber(redis.call('get', KEYS[2]) or 0)
        
        local new_tokens = math.min(
            capacity,
            current + ((now - last_update) * rate)
        )
        
        if new_tokens >= 1 then
            redis.call('set', KEYS[1], new_tokens - 1)
            redis.call('set', KEYS[2], now)
            return 1
        end
        return 0
        """
        return self.redis.eval(script, 2, key)
```

## 4. Pagination Strategies
**Question:** Why does Offset pagination degrade for large datasets?
A. CPU limits
B. Full table scan with skipped rows
C. Index corruption
D. Cursor invalidation
ANSWER: B

Cursor pagination is more suitable than offset pagination in:
A. Analytics queries
B. Real-time feeds (e.g., Twitter, FB)
C. Small static datasets
D. Batch processing
ANSWER: B

Which issue is most likely avoided by cursor pagination?
A. Phantom reads
B. Duplicate/missing rows due to inserts/deletes
C. Slow writes
D. Deadlocks
ANSWER: B

At which OSI layer does an Application Load Balancer operate?
A. Layer 2
B. Layer 3
C. Layer 4
D. Layer 7
ANSWER: D

Which algorithm ensures that clients with the same IP always hit the same server?
A. Round Robin
B. Least Connections
C. IP Hash
D. Random
ANSWER: C

Which is not a common load balancer feature?
A. SSL termination
B. Health checks
C. Circuit breaking
D. Caching
ANSWER: C

When the circuit breaker is in half-open state, it:
A. Rejects all requests
B. Allows all requests
C. Allows limited test requests
D. Closes permanently
ANSWER: C

Which failure scenario motivates using a circuit breaker?
A. High CPU usage
B. Downstream timeout propagation
C. Duplicate requests
D. Cache misses
ANSWER: B

Circuit breakers primarily prevent:
A. Network congestion
B. Cascading failures
C. Deadlocks
D. Resource starvation
ANSWER: B

In a network partition, a system cannot provide both:
A. Consistency and Latency
B. Consistency and Availability
C. Partition tolerance and Latency
D. Durability and Partition tolerance
ANSWER: B

DynamoDB is classified as:
A. CP
B. AP
C. CA
D. PC
ANSWER: B

A financial system requiring strict account balances across replicas would likely prefer:
A. CP system
B. AP system
C. Eventually consistent
D. BASE model
ANSWER: A

PACELC extends CAP by adding which tradeoff when no partition exists?
A. Durability vs Performance
B. Latency vs Consistency
C. Partition vs Availability
D. Fault tolerance vs Throughput
ANSWER: B

Google Spanner is classified as:
A. PA/EL
B. PC/EC
C. CP/AP
D. CA
ANSWER: B

Which theorem better represents real-world distributed DBs?
A. CAP
B. PACELC
C. FLP
D. BASE
ANSWER: B

## 5. HTTP Methods and Idempotency
**Question:** Why is POST usually non-idempotent?
A. Multiple calls create duplicates
B. Stateless nature of HTTP
C. Caching conflicts
D. Authentication required
**Answer: A. Multiple calls create duplicates**

### Detailed Explanation
#### Why A is correct:
1. **POST Behavior:**
   - Creates new resource each time
   - No built-in deduplication
   - State changes with each call

2. **Example:**
```javascript
// Multiple identical POST requests create multiple orders
app.post('/orders', (req, res) => {
    const order = new Order(req.body);
    order.save()  // Creates new record each time
        .then(saved => res.status(201).json(saved));
});
```

#### Why other options are incorrect:
1. **Stateless nature of HTTP (B):**
   - Applies to all HTTP methods
   - Not specific to POST idempotency

2. **Caching conflicts (C):**
   - POST responses aren't cached by default
   - Not related to idempotency

3. **Authentication required (D):**
   - Security concern, not idempotency
   - Applies to all methods

### Best Practices
1. **Idempotency Key Implementation:**
```javascript
// Express middleware for idempotency
const idempotencyMiddleware = async (req, res, next) => {
    const key = req.headers['idempotency-key'];
    if (!key) {
        return res.status(400).json({
            error: 'Idempotency-Key required'
        });
    }

    const cached = await cache.get(key);
    if (cached) {
        return res.status(200).json(cached);
    }

    // Store original response
    res.originalJson = res.json;
    res.json = function(data) {
        cache.set(key, data);
        res.originalJson(data);
    };
    next();
};
```

## 6. Idempotency in API Design
**Question:** How does an Idempotency-Key prevent duplicate booking?
A. Server ignores POST body
B. Server checks key before creating resource
C. Client retries endlessly
D. Cache invalidation
**Answer: B. Server checks key before creating resource**

### Detailed Explanation
#### Implementation Example
```typescript
interface IdempotencyRecord {
    key: string;
    response: any;
    createdAt: Date;
}

class IdempotencyService {
    private cache: Map<string, IdempotencyRecord>;

    async processRequest(key: string, operation: () => Promise<any>) {
        // Check for existing response
        const existing = await this.cache.get(key);
        if (existing) {
            return existing.response;
        }

        // Execute operation and store result
        const result = await operation();
        await this.cache.set(key, {
            key,
            response: result,
            createdAt: new Date()
        });

        return result;
    }
}
```

### Real-World Examples
1. **Payment Processing:**
```javascript
app.post('/payments', async (req, res) => {
    const idempotencyKey = req.headers['idempotency-key'];
    
    try {
        const result = await idempotencyService.processRequest(
            idempotencyKey,
            async () => {
                // Process payment
                const payment = await stripe.charges.create({
                    amount: req.body.amount,
                    currency: 'usd',
                    source: req.body.token,
                    idempotency_key: idempotencyKey
                });
                return payment;
            }
        );
        res.json(result);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});
```

2. **Distributed Systems:**
```python
class DistributedIdempotency:
    def __init__(self, redis_client):
        self.redis = redis_client

    async def process(self, key, operation):
        lock = await self.redis.lock(f"lock:{key}")
        try:
            # Acquire distributed lock
            await lock.acquire()
            
            # Check for existing result
            result = await self.redis.get(f"idempotency:{key}")
            if result:
                return json.loads(result)
            
            # Execute operation
            result = await operation()
            
            # Store result
            await self.redis.set(
                f"idempotency:{key}",
                json.dumps(result),
                ex=3600  # 1 hour expiry
            )
            return result
            
        finally:
            await lock.release()
```

## 7. Payment Systems Idempotency
**Question:** In payment systems, idempotent POSTs prevent:
A. Overwrites
B. Double charges
C. Session expiry
D. Network delay
**Answer: B. Double charges**

### Detailed Explanation
#### Why B is correct:
1. **Payment Processing Safety:**
   - Prevents duplicate transactions
   - Handles network timeouts safely
   - Ensures exactly-once processing

2. **Implementation Example:**
```typescript
class PaymentProcessor {
    async processPayment(paymentInfo: PaymentInfo, idempotencyKey: string) {
        const existing = await this.findExistingTransaction(idempotencyKey);
        if (existing) {
            return existing;
        }

        const paymentIntent = await stripe.paymentIntents.create({
            amount: paymentInfo.amount,
            currency: paymentInfo.currency,
            payment_method: paymentInfo.paymentMethodId,
            idempotency_key: idempotencyKey,
            confirm: true
        });

        await this.saveTransaction(idempotencyKey, paymentIntent);
        return paymentIntent;
    }
}
```

### Best Practices
1. **Idempotency Key Generation:**
```javascript
const generateIdempotencyKey = (paymentInfo) => {
    const data = `${paymentInfo.customerId}-${paymentInfo.amount}-${Date.now()}`;
    return crypto.createHash('sha256').update(data).digest('hex');
};
```

2. **Error Handling:**
```javascript
try {
    const payment = await processPayment(paymentInfo, idempotencyKey);
    res.json(payment);
} catch (error) {
    if (error.code === 'idempotency_key_reused') {
        // Return original response
        const original = await getOriginalResponse(idempotencyKey);
        res.json(original);
    } else {
        res.status(500).json({ error: error.message });
    }
}
```

## 8. HTTP Caching
**Question:** Which status code is returned if cached content is still valid?
A. 200 OK
B. 202 Accepted
C. 304 Not Modified
D. 503 Service Unavailable
**Answer: C. 304 Not Modified**

### Detailed Explanation
#### Cache Validation Flow
1. **Client Request:**
```http
GET /resource HTTP/1.1
If-None-Match: "abc123"
If-Modified-Since: Wed, 21 Aug 2025 12:00:00 GMT
```

2. **Server Response (Not Modified):**
```http
HTTP/1.1 304 Not Modified
ETag: "abc123"
Cache-Control: max-age=3600
```

#### Implementation Example
```javascript
app.get('/api/data', (req, res) => {
    const etag = calculateETag(data);
    
    if (req.headers['if-none-match'] === etag) {
        res.status(304).end();
    } else {
        res.set('ETag', etag);
        res.json(data);
    }
});
```

## 9. Cache Freshness
**Question:** A stale cache is refreshed using which mechanisms?
A. ETag, Last-Modified
B. SSL handshake
C. CDN hashing
D. DNS lookup
**Answer: A. ETag, Last-Modified**

### Detailed Explanation
#### Cache Validation Mechanisms
1. **ETag Implementation:**
```javascript
const calculateETag = (content) => {
    return crypto
        .createHash('md5')
        .update(JSON.stringify(content))
        .digest('hex');
};

app.get('/api/resource', (req, res) => {
    const data = fetchResource();
    const etag = calculateETag(data);

    if (req.headers['if-none-match'] === etag) {
        return res.status(304).end();
    }

    res.set({
        'ETag': etag,
        'Cache-Control': 'public, max-age=3600'
    }).json(data);
});
```

2. **Last-Modified Implementation:**
```javascript
app.get('/api/resource', (req, res) => {
    const resource = fetchResource();
    const lastModified = resource.updatedAt.toUTCString();

    if (req.headers['if-modified-since'] === lastModified) {
        return res.status(304).end();
    }

    res.set({
        'Last-Modified': lastModified,
        'Cache-Control': 'public, max-age=3600'
    }).json(resource);
});
```

### Cache Headers
1. **Cache-Control Options:**
```http
Cache-Control: public, max-age=3600
Cache-Control: private, no-cache
Cache-Control: no-store
Cache-Control: must-revalidate
```

2. **Conditional Requests:**
```javascript
const conditionalGet = async (url, etag) => {
    const response = await fetch(url, {
        headers: {
            'If-None-Match': etag
        }
    });

    if (response.status === 304) {
        return getCachedData(url);
    }

    const data = await response.json();
    await cacheData(url, data, response.headers.get('etag'));
    return data;
};
```

## 10. Edge Computing and CDN
**Question:** Edge caching reduces:
A. Database consistency issues
B. Bandwidth and latency
C. Authentication overhead
D. CAP tradeoffs
**Answer: B. Bandwidth and latency**

### Detailed Explanation
#### Why B is correct:
1. **Edge Cache Benefits:**
   - Content closer to users
   - Reduced origin server load
   - Better response times
   - Lower bandwidth costs

2. **Implementation Example:**
```javascript
// CloudFront distribution setup
const distribution = new aws.cloudfront.Distribution("myDist", {
    origins: [{
        domainName: "origin.example.com",
        originId: "myOrigin",
        customOriginConfig: {
            httpPort: 80,
            httpsPort: 443,
            originProtocolPolicy: "https-only",
        },
    }],
    defaultCacheBehavior: {
        targetOriginId: "myOrigin",
        viewerProtocolPolicy: "redirect-to-https",
        allowedMethods: ["GET", "HEAD", "OPTIONS"],
        cachedMethods: ["GET", "HEAD"],
        forwardedValues: {
            queryString: false,
            cookies: {
                forward: "none",
            },
        },
        minTtl: 0,
        defaultTtl: 3600,
        maxTtl: 86400,
    },
});
```

#### Cache Invalidation Strategy
```javascript
// AWS CloudFront invalidation
const invalidation = new aws.cloudfront.Distribution("invalidation", {
    distributionId: distribution.id,
    paths: ["/content/*"],
});
```

## 11. Feed API Design
**Question:** You design a news feed API requiring real-time, no duplicates. Which pagination?
A. Offset
B. Cursor
C. Random sampling
D. Keyset
**Answer: B. Cursor**

### Detailed Explanation
#### Why Cursor Pagination is Best:
1. **Real-time Support:**
   - Handles new items correctly
   - No skipping or duplicates
   - Consistent with data changes

2. **Implementation Example:**
```typescript
interface FeedItem {
    id: string;
    content: string;
    timestamp: Date;
}

class FeedService {
    async getFeed(cursor?: string, limit: number = 20): Promise<{
        items: FeedItem[];
        nextCursor: string;
    }> {
        const query = cursor 
            ? { timestamp: { $lt: new Date(cursor) } }
            : {};

        const items = await FeedItem
            .find(query)
            .sort({ timestamp: -1 })
            .limit(limit + 1)
            .toArray();

        const hasMore = items.length > limit;
        if (hasMore) {
            items.pop();
        }

        return {
            items,
            nextCursor: hasMore 
                ? items[items.length - 1].timestamp.toISOString()
                : null
        };
    }
}
```

### API Response Structure
```javascript
// GraphQL Schema
const typeDefs = gql`
  type FeedConnection {
    edges: [FeedEdge!]!
    pageInfo: PageInfo!
  }

  type FeedEdge {
    node: FeedItem!
    cursor: String!
  }

  type PageInfo {
    hasNextPage: Boolean!
    endCursor: String
  }

  type FeedItem {
    id: ID!
    content: String!
    createdAt: DateTime!
  }
`;

// REST API Response
{
    "data": {
        "items": [{
            "id": "123",
            "content": "News item",
            "createdAt": "2025-08-21T12:00:00Z"
        }],
        "pageInfo": {
            "hasNextPage": true,
            "nextCursor": "2025-08-21T12:00:00Z"
        }
    }
}
```

### Real-time Updates
```typescript
class RealtimeFeedService {
    private subscribers: Map<string, WebSocket> = new Map();

    async publishUpdate(item: FeedItem) {
        // Store in database
        await this.store(item);

        // Notify subscribers
        this.subscribers.forEach(ws => {
            ws.send(JSON.stringify({
                type: 'FEED_UPDATE',
                data: item
            }));
        });
    }

    subscribeFeed(userId: string, ws: WebSocket) {
        this.subscribers.set(userId, ws);

        ws.on('close', () => {
            this.subscribers.delete(userId);
        });
    }
}
```

### Deduplication Strategy
```typescript
class FeedDeduplicator {
    private seenItems: Set<string> = new Set();

    deduplicate(items: FeedItem[]): FeedItem[] {
        return items.filter(item => {
            const key = this.getDeduplicationKey(item);
            if (this.seenItems.has(key)) {
                return false;
            }
            this.seenItems.add(key);
            return true;
        });
    }

    private getDeduplicationKey(item: FeedItem): string {
        return `${item.type}:${item.contentHash}`;
    }
}
```

A service hits a downstream dependency with 95% timeouts. Which resilience pattern helps first?
A. Circuit breaker
B. Load balancing
C. Edge caching
D. PACELC
ANSWER: A

An API gateway enforces 10 requests/sec per user. Which mechanism fits?
A. CAP theorem
B. Token Bucket
C. Idempotent POST
D. Circuit breaker
ANSWER: B

CDN responds instantly to a video request from India. Likely cause?
A. Cache miss
B. Cache hit at edge
C. Token Bucket
D. PACELC
ANSWER: B

## 3. CAP Theorem in Practice
**Question:** Which system is most likely AP under CAP?
A. Spanner
B. HBase
C. DynamoDB
D. Mongo (in strong consistency mode)
**Answer: C. DynamoDB**

### Detailed Explanation
#### Why DynamoDB is AP (Availability/Partition Tolerance):
1. **Architecture Features:**
   - Multi-region replication
   - Eventually consistent reads by default
   - Configurable consistency levels
   - Automatic failover

2. **Implementation Example:**
```javascript
// DynamoDB with eventual consistency (default)
const params = {
    TableName: 'Users',
    Key: {
        'userId': '123'
    }
};

// vs Strong consistency
const paramsStrong = {
    TableName: 'Users',
    Key: {
        'userId': '123'
    },
    ConsistentRead: true  // More latency, less availability
};
```

#### Why other options are incorrect:
1. **Spanner (A):**
   - CP system (Consistency/Partition Tolerance)
   - Uses TrueTime for strong consistency
   - Sacrifices availability for consistency

2. **HBase (B):**
   - CP system
   - Strong consistency model
   - Master-slave architecture

3. **Mongo in strong consistency mode (D):**
   - CP when configured for strong consistency
   - Sacrifices availability for consistency

### Best Practices
1. **Choosing Consistency Level:**
```javascript
// Example with AWS SDK v3
const dynamodb = new DynamoDBClient({
    region: "us-west-2",
    // For eventual consistency (AP)
    readConsistency: "eventual",
    
    // For strong consistency (CP)
    // readConsistency: "strong"
});
```

2. **Multi-Region Setup:**
```javascript
const dynamodb = new DynamoDBClient({
    region: "us-west-2",
    replicationRegions: ["us-east-1", "eu-west-1"],
    // Configure conflict resolution
    conflictResolver: (existing, incoming) => {
        return incoming.timestamp > existing.timestamp ? 
               incoming : existing;
    }
});
```

## 4. PACELC Theorem
**Question:** If consistency is preferred over latency in normal conditions, the system follows:
A. PA/EL
B. PC/EC
C. AP/EC
D. CP
**Answer: B. PC/EC**

### Detailed Explanation
#### Why PC/EC is correct:
1. **PACELC Breakdown:**
   - P: Partition tolerance
   - A/C: Availability vs Consistency during partitions
   - E: Normal operation
   - L/C: Latency vs Consistency in normal operation

2. **PC/EC Characteristics:**
   - Prefers Consistency over Availability during partitions
   - Prefers Consistency over Latency during normal operation

#### Implementation Example
```java
// Example with a distributed lock using Redis
public class DistributedLock {
    private final RedisClient redis;
    private final String lockKey;
    private final int timeoutMs;

    public boolean acquireLock() {
        // Prefer consistency (PC/EC)
        return redis.set(lockKey, "1", 
                        SetArgs.Builder.nx()
                                     .px(timeoutMs));
    }
}
```

### System Examples
1. **PC/EC Systems:**
```sql
-- PostgreSQL with synchronous replication
ALTER SYSTEM SET synchronous_commit = on;
ALTER SYSTEM SET synchronous_standby_names = 'node1,node2';
```

2. **PA/EL Systems:**
```javascript
// Cassandra with LOCAL_ONE consistency
const query = {
    consistency: cassandra.types.consistencies.LOCAL_ONE,
    query: 'SELECT * FROM users WHERE userid = ?'
};
```

3. **Hybrid Approaches:**
```python
class DataStore:
    def read(self, key, consistency_level):
        if consistency_level == "STRONG":
            # PC/EC path - synchronous replication
            return self._read_all_replicas(key)
        else:
            # PA/EL path - async replication
            return self._read_any_replica(key)
```
