# Kubernetes Components and Architecture

## 1. Cluster State Storage
**Question:** Which Kubernetes component is the "single source of truth" for cluster state?
A. kubelet
B. kube-scheduler
C. etcd
D. kube-proxy
**Answer: C. etcd**

### Detailed Explanation
#### Why etcd is correct:
- etcd is a distributed key-value store that maintains:
  - Cluster configuration
  - Node status and information
  - Pod definitions and states
  - ConfigM### 19. Service Accessibility
**Question:** Kubernetes Service of type `ClusterIP` is accessible:
A. Only within the cluster
B. From external network
C. Only from Pod's namespace
D. Nowhere unless exposed
**Answer: A. Only within the cluster**

#### Service Types Overview
1. **ClusterIP Service:**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: internal-service
spec:
  type: ClusterIP
  selector:
    app: backend
  ports:
  - port: 80
    targetPort: 8080
```

2. **NodePort Service:**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: external-service
spec:
  type: NodePort
  selector:
    app: frontend
  ports:
  - port: 80
    targetPort: 8080
    nodePort: 30080
```

### 20. Deployment Features
**Question:** Which Kubernetes controller is responsible for rolling updates and rollbacks?
A. ReplicaSet
B. Deployment
C. StatefulSet
D. DaemonSet
**Answer: B. Deployment**

#### Deployment Strategies
1. **Rolling Update (Default):**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
spec:
  replicas: 3
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
```

2. **Rollback Command:**
```bash
# View revision history
kubectl rollout history deployment/nginx-deployment

# Rollback to previous version
kubectl rollout undo deployment/nginx-deployment
```

### 21. Pod Scheduling
**Question:** Which Kubernetes component is responsible for scheduling Pods to nodes?
A. Controller Manager
B. Scheduler
C. Kubelet
D. API Server
**Answer: B. Scheduler**

#### Scheduling Features
1. **Node Selection:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  nodeSelector:
    disktype: ssd
```

2. **Pod Affinity:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: web-pod
spec:
  affinity:
    podAffinity:
      requiredDuringSchedulingIgnoredDuringExecution:
      - labelSelector:
          matchExpressions:
          - key: app
            operator: In
            values:
            - store
        topologyKey: "kubernetes.io/hostname"
```

3. **Taints and Tolerations:**
```yaml
# Add taint to node
kubectl taint nodes node1 key=value:NoSchedule

# Pod with toleration
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  tolerations:
  - key: "key"
    operator: "Equal"
    value: "value"
    effect: "NoSchedule"
``` Service endpoints
  - Resource quotas and limits

#### Why other options are incorrect:
1. **kubelet (A):**
   - Only manages containers on a single node
   - Doesn't store cluster-wide information
   - Reads state from API server

2. **kube-scheduler (B):**
   - Only makes scheduling decisions
   - Consumes state from etcd via API server
   - Doesn't store persistent data

3. **kube-proxy (D):**
   - Handles network routing
   - No storage capabilities
   - Depends on etcd for service information

### Best Practices for etcd
1. **Backup Strategy:**
```bash
# Create etcd snapshot
ETCDCTL_API=3 etcdctl snapshot save snapshot.db

# Verify snapshot
ETCDCTL_API=3 etcdctl snapshot status snapshot.db
```

2. **High Availability:**
- Deploy odd number of etcd instances (3, 5, 7)
- Use separate disks for etcd data
- Monitor etcd metrics

## 2. API Server Availability
**Question:** What happens if the kube-apiserver goes down?
A. Pods stop running
B. Existing workloads run, but no new changes can be applied
C. Scheduler takes over API tasks
D. kube-proxy replaces apiserver
**Answer: B. Existing workloads run, but no new changes can be applied**

### Detailed Explanation
#### Why B is correct:
- Kubernetes uses a decentralized architecture
- Node components (kubelet, kube-proxy) continue functioning
- Existing pods and services remain operational
- Only cluster management operations are affected

#### Why other options are incorrect:
1. **Pods stop running (A):**
   - False because pods are managed by kubelet locally
   - Kubelet continues to run and monitor pods
   - Container runtime remains functional

2. **Scheduler takes over (C):**
   - Scheduler is a client of the API server
   - Cannot replace API server functionality
   - Lacks authentication and authorization capabilities

3. **kube-proxy replaces (D):**
   - kube-proxy only handles networking
   - No capability to handle API requests
   - Different architectural purposeubernetes component is the “single source of truth” for cluster state?
A. kubelet
B. kube-scheduler
C. etcd
D. kube-proxy
ANSWER: C

2. What happens if the kube-apiserver goes down?
A. Pods stop running
B. Existing workloads run, but no new changes can be applied
C. Scheduler takes over API tasks
D. kube-proxy replaces apiserver
ANSWER: B

3. What is the role of the kube-scheduler?
A. Runs pods
B. Decides node placement for pods
C. Manages network rules
D. Stores cluster state
ANSWER: B

4. Which best describes the kubelet?
A. Stores cluster data
B. Assigns pods to nodes
C. Ensures containers on a node run as specified
D. Manages traffic routing
ANSWER: C

5. What does kube-proxy do?
A. Schedules pods to nodes
B. Stores cluster metadata
C. Implements Services by routing traffic to pod IPs
D. Monitors pod health
ANSWER: C

6. Which describes the function of a CNI plugin like Calico or Flannel?
A. Runs containers
B. Provides pod networking across nodes
C. Schedules workloads
D. Monitors logs
ANSWER: B

7. Which Service type is only reachable inside the cluster?
A. ClusterIP
B. NodePort
C. LoadBalancer
D. ExternalName
ANSWER: A

8. What role does CoreDNS play in Kubernetes?
A. Stores logs
B. Provides pod-to-pod networking
C. Resolves service names to ClusterIPs
D. Runs system metrics
ANSWER: C

9. How are ConfigMaps and Secrets different?
A. ConfigMaps are for sensitive data, Secrets for non-sensitive
B. Secrets can be encrypted, ConfigMaps are plain configs
C. ConfigMaps are stored in files, Secrets are not
D. ConfigMaps can’t be mounted into pods
ANSWER: B

10. What is the difference between a Deployment and a StatefulSet?
A. Deployments are for stateless apps; StatefulSets provide stable identity and storage
B. StatefulSets scale faster
C. Deployments only support 1 replica
D. StatefulSets don’t need volumes
ANSWER: A

## Service Types and Networking

### 11. NodePort Service Request Flow
**Question:** When an external client accesses a NodePort Service, which component first processes the packet on the node?
A. kubelet
B. kube-proxy
C. Ingress
D. container runtime
**Answer: B. kube-proxy**

#### Detailed Explanation
##### Why kube-proxy is correct:
- First point of contact for incoming service traffic
- Manages iptables/IPVS rules for service routing
- Handles NAT and load balancing
- Processes packets before they reach containers

##### Why other options are incorrect:
1. **kubelet (A):**
   - Manages container lifecycle
   - Not involved in network routing
   - Only handles pod operations

2. **Ingress (C):**
   - Layer 7 load balancer
   - Requires NodePort/LoadBalancer service
   - Not involved in direct node traffic

3. **container runtime (D):**
   - Only manages container execution
   - Doesn't handle network routing
   - Works after network setup

### 12. LoadBalancer Service Flow
**Question:** In a LoadBalancer Service, what is the correct sequence?
A. LB → NodePort → kube-proxy → Pod IP
B. LB → kubelet → Pod
C. LB → kube-proxy → Service → Pod
D. LB → Ingress → Pod
**Answer: A. LB → NodePort → kube-proxy → Pod IP**

#### Implementation Example
```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-loadbalancer
spec:
  type: LoadBalancer
  ports:
  - port: 80
    targetPort: 8080
  selector:
    app: myapp
```

### 13. Service Load Balancing
**Question:** How does kube-proxy decide which pod to send a request to?
A. By hashing client IP
B. Round-robin or IPVS load-balancing
C. Randomly chosen by kubelet
D. DNS chooses pod directly
**Answer: B. Round-robin or IPVS load-balancing**

#### Load Balancing Modes
1. **iptables mode:**
```bash
# View iptables rules
iptables-save | grep KUBE-SVC
```

2. **IPVS mode:**
```bash
# Enable IPVS mode
--proxy-mode=ipvs
```

### 14. Network Troubleshooting
**Question:** A request reaches a pod but no response returns. What is the likely issue?
A. Service not created
B. Readiness probe failed
C. Misconfigured CNI return path
D. kube-proxy missing
**Answer: C. Misconfigured CNI return path**

#### Network Debugging
```bash
# Check CNI configuration
ls /etc/cni/net.d/

# Verify pod networking
kubectl exec -it pod-name -- ip route

# Test connectivity
kubectl exec -it pod-name -- ping service-name
```

### 15. Internal Service Communication
**Question:** What is the correct trajectory for an in-cluster pod-to-service request?
A. Pod → DNS (CoreDNS) → ClusterIP → kube-proxy → Pod
B. Pod → kubelet → Pod directly
C. Pod → kube-proxy → DNS → Pod
D. Pod → Ingress → ClusterIP → Pod
**Answer: A. Pod → DNS (CoreDNS) → ClusterIP → kube-proxy → Pod**

#### Service Discovery Flow
1. **DNS Resolution:**
```yaml
# CoreDNS ConfigMap
apiVersion: v1
kind: ConfigMap
metadata:
  name: coredns
  namespace: kube-system
data:
  Corefile: |
    .:53 {
        errors
        health
        kubernetes cluster.local in-addr.arpa ip6.arpa {
           pods insecure
           upstream
           fallthrough in-addr.arpa ip6.arpa
        }
        prometheus :9153
        forward . /etc/resolv.conf
        cache 30
        loop
        reload
        loadbalance
    }

## Pod Architecture and Management

### 16. Pod Container Structure
**Question:** In Kubernetes, a Pod can contain:
A. Exactly 1 container
B. Multiple containers sharing the same network namespace
C. Only sidecar containers
D. Containers that must run on different nodes
**Answer: B. Multiple containers sharing the same network namespace**

#### Detailed Explanation
##### Why B is correct:
- Pods are the smallest deployable unit
- All containers in a pod share:
  - Network namespace
  - IPC namespace
  - Storage volumes
  - Same IP address

##### Multi-Container Patterns:
1. **Sidecar Pattern:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: webserver
spec:
  containers:
  - name: nginx
    image: nginx
  - name: log-aggregator
    image: log-collector
    volumeMounts:
    - name: logs
      mountPath: /var/log/nginx
```

2. **Ambassador Pattern:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: redis-proxy
spec:
  containers:
  - name: redis
    image: redis
  - name: ambassador
    image: ambassador-proxy
    ports:
    - containerPort: 6379
```

### 17. Pod Lifecycle
**Question:** What is the default restart policy for a Kubernetes Pod?
A. Never
B. OnFailure
C. Always
D. Manual
**Answer: C. Always**

#### Restart Policies
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  restartPolicy: Always  # Default
  containers:
  - name: app
    image: myapp
```

##### Policy Behaviors:
1. **Always:**
   - Restarts on any termination
   - Best for long-running services

2. **OnFailure:**
   - Restarts only on error (non-zero exit)
   - Good for batch jobs

3. **Never:**
   - No automatic restarts
   - Used for one-time tasks

### 18. Pod Controllers
**Question:** Which object in Kubernetes ensures a set of identical Pods are always running?
A. Pod
B. ReplicaSet
C. Service
D. ConfigMap
**Answer: B. ReplicaSet**

#### Controller Types
1. **ReplicaSet:**
```yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: frontend
spec:
  replicas: 3
  selector:
    matchLabels:
      tier: frontend
  template:
    metadata:
      labels:
        tier: frontend
    spec:
      containers:
      - name: php-redis
        image: gcr.io/google_samples/gb-frontend:v3
```

2. **Deployment (Higher-level Controller):**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.14.2
        ports:
        - containerPort: 80
```

#### Best Practices
1. **Health Checks:**
```yaml
spec:
  containers:
  - name: app
    livenessProbe:
      httpGet:
        path: /health
        port: 8080
    readinessProbe:
      httpGet:
        path: /ready
        port: 8080
```

2. **Resource Management:**
```yaml
spec:
  containers:
  - name: app
    resources:
      requests:
        memory: "64Mi"
        cpu: "250m"
      limits:
        memory: "128Mi"
        cpu: "500m"
```
A. Pod
B. ReplicaSet
C. Service
D. ConfigMap
ANSWER: B

19. Kubernetes Service of type `ClusterIP` is accessible:
A. Only within the cluster
B. From external network
C. Only from Pod’s namespace
D. Nowhere unless exposed
ANSWER: A

20. Which Kubernetes controller is responsible for rolling updates and rollbacks?
A. ReplicaSet
B. Deployment
C. StatefulSet
D. DaemonSet
ANSWER: B

21. Which Kubernetes component is responsible for scheduling Pods to nodes?
A. Controller Manager
B. Scheduler
C. Kubelet
D. API Server
ANSWER: B

### 22. Configuration Management
**Question:** ConfigMap vs Secret: which statement is correct?
A. Both are encrypted by default
B. Secrets are base64-encoded, ConfigMaps store plain text
C. ConfigMaps require RBAC, Secrets do not
D. Secrets cannot be mounted in Pods
**Answer: B. Secrets are base64-encoded, ConfigMaps store plain text**

#### Configuration Examples
1. **ConfigMap:**
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  app.properties: |
    environment=production
    debug=false
  cache.json: |
    {
      "timeout": 3600,
      "maxItems": 100
    }
```

2. **Secret:**
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-secrets
type: Opaque
data:
  username: dXNlcm5hbWU=  # base64 encoded
  password: cGFzc3dvcmQ=  # base64 encoded
```

### 23. Pod Lifecycle Management
**Question:** If a Pod crashes, which component detects it and attempts to restart it?
A. Scheduler
B. Kubelet
C. Controller Manager
D. etcd
**Answer: B. Kubelet**

#### Pod Health Monitoring
1. **Liveness Probe:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: app
spec:
  containers:
  - name: app
    livenessProbe:
      httpGet:
        path: /health
        port: 8080
      initialDelaySeconds: 15
      periodSeconds: 10
```

2. **Restart Policy:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: app
spec:
  restartPolicy: Always
  containers:
  - name: app
    image: myapp
```

### 24. DaemonSet Usage
**Question:** Which Kubernetes object ensures only one Pod is scheduled per node?
A. DaemonSet
B. StatefulSet
C. Job
D. Deployment
**Answer: A. DaemonSet**

#### DaemonSet Examples
1. **Monitoring Agent:**
```yaml
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: node-exporter
spec:
  selector:
    matchLabels:
      name: node-exporter
  template:
    metadata:
      labels:
        name: node-exporter
    spec:
      tolerations:
      - key: node-role.kubernetes.io/master
        effect: NoSchedule
      containers:
      - name: node-exporter
        image: prom/node-exporter
```

2. **Log Collector:**
```yaml
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: fluentd
spec:
  selector:
    matchLabels:
      name: fluentd
  template:
    metadata:
      labels:
        name: fluentd
    spec:
      containers:
      - name: fluentd
        image: fluentd:v1.7
        volumeMounts:
        - name: varlog
          mountPath: /var/log
      volumes:
      - name: varlog
        hostPath:
          path: /var/log
```

### 25. Ingress Controller
**Question:** Kubernetes Ingress is used to:
A. Load balance between Pods within a namespace
B. Manage traffic rules and expose services externally
C. Replace Service objects completely
D. Create persistent storage
**Answer: B. Manage traffic rules and expose services externally**

#### Ingress Configuration
1. **Basic Ingress:**
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: minimal-ingress
spec:
  rules:
  - host: myapp.example.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: myapp-service
            port:
              number: 80
```

2. **TLS Configuration:**
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: tls-ingress
spec:
  tls:
  - hosts:
    - myapp.example.com
    secretName: tls-secret
  rules:
  - host: myapp.example.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: myapp-service
            port:
              number: 80
```

3. **Multiple Services:**
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: multi-service
spec:
  rules:
  - host: myapp.example.com
    http:
      paths:
      - path: /api
        pathType: Prefix
        backend:
          service:
            name: api-service
            port:
              number: 80
      - path: /web
        pathType: Prefix
        backend:
          service:
            name: web-service
            port:
              number: 80
```
