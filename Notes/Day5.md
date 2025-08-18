
1. In a paged memory system, increasing the page size generally: 
A. Increases internal fragmentation but reduces page table size 
B. Decreases internal fragmentation and increases page table size 
C. Has no impact on page table size 
D. Eliminates page faults completely
**ANSWER: A**
**Explanation:**
- **A (Correct):** Larger page sizes mean each process's memory is divided into fewer, bigger pages. This reduces the number of entries in the page table (smaller page table), but increases wasted space within each page (internal fragmentation). For example, if a process needs 9KB and the page size is 8KB, it will use two pages (16KB), wasting 7KB.
- **B:** Decreasing internal fragmentation would require smaller pages, not larger. Larger pages increase, not decrease, internal fragmentation.
- **C:** Page table size is directly affected by the number of pages; larger pages mean fewer pages and a smaller table.
- **D:** Page faults are not eliminated by increasing page size; they depend on access patterns and available memory.


2. Which of the following is least likely to cause a page fault? 
A. Accessing a page for the first time
B. Accessing a page already in main memory 
C. Accessing a swapped-out page 
D. Accessing a memory location not mapped to any page
**ANSWER: B**
**Explanation:**
- **B (Correct):** If a page is already in main memory, accessing it will not cause a page fault; the data is immediately available.
- **A:** Accessing a page for the first time will likely cause a page fault, as it is not yet loaded.
- **C:** Accessing a swapped-out page (not in memory) will cause a page fault to bring it back in.
- **D:** Accessing an unmapped memory location may cause a segmentation fault or an invalid page fault.


3. In a system using demand paging, the effective access time (EAT) is: 
A. Always equal to main memory access time 
B. Weighted sum of memory access time and page fault service time 
C. Sum of main memory access time and page fault service time 
D. Main memory access time multiplied by hit ratio
**ANSWER: B**
**Explanation:**
- **B (Correct):** EAT = (1 - p) * memory access time + p * (page fault service time), where p is the probability of a page fault. This is a weighted sum.
- **A:** EAT is only equal to memory access time if there are no page faults (p=0).
- **C:** The sum would only be correct if every access caused a page fault, which is not the case.
- **D:** Multiplying by hit ratio is not the correct formula.


4. The “working set” model helps in: 
A. Preventing segmentation faults 
B. Estimating the minimum number of frames needed to avoid thrashing 
C. Reducing internal fragmentation 
D. Improving CPU scheduling
**ANSWER: B**
**Explanation:**
- **B (Correct):** The working set is the set of pages a process is actively using. By tracking it, the OS can allocate enough frames to avoid thrashing (excessive paging).
- **A:** Segmentation faults are unrelated to the working set model.
- **C:** Internal fragmentation is a function of page size, not the working set.
- **D:** CPU scheduling is not directly related to the working set.


5. Belady’s anomaly occurs in: 
A. FIFO page replacement 
B. LRU page replacement 
C. Optimal page replacement 
D. All of the above
**ANSWER: A**
**Explanation:**
- **A (Correct):** Belady’s anomaly is the counterintuitive situation where increasing the number of page frames increases the number of page faults. This is possible with FIFO.
- **B, C:** LRU and Optimal algorithms do not exhibit Belady’s anomaly.
- **D:** Not all algorithms show this anomaly; only FIFO and some others do.
**Example:**
Reference string: 1,2,3,4,1,2,5,1,2,3,4,5. FIFO with 3 frames has more faults than with 4 frames.


6. Shared memory IPC is generally: 
A. Slower than message passing 
B. Faster than message passing 
C. Always OS-dependent and slower than sockets 
D. Not possible in Linux
**ANSWER: B**
**Explanation:**
- **B (Correct):** Shared memory allows processes to communicate by reading/writing to a common memory area, which is much faster than sending messages via the kernel.
- **A:** Shared memory is not slower; it is the fastest IPC method.
- **C:** Shared memory is not always slower than sockets and is supported on Linux.
- **D:** Shared memory is possible in Linux (e.g., POSIX and System V shared memory).


7. In UNIX System V shared memory, shmat() is used for: 
A. Creating a shared memory segment
B. Attaching a existing shared memory segment 
C. Removing a shared memory segment 
D. Locking shared memory
**ANSWER: B**
**Explanation:**
- **B (Correct):** `shmat()` attaches an existing shared memory segment to the process's address space.
- **A:** `shmget()` is used to create a segment.
- **C:** `shmctl()` with IPC_RMID is used to remove.
- **D:** Locking is not the purpose of `shmat()`.


8. The biggest challenge with shared memory IPC is: 
A. High latency 
B. Synchronization between processes 
C. Lack of portability 
D. Expensive system calls per read/write
**ANSWER: B**
**Explanation:**
- **B (Correct):** Since multiple processes can access shared memory at the same time, synchronization (e.g., using semaphores) is needed to avoid race conditions.
- **A:** Shared memory is low latency.
- **C:** Shared memory is portable across UNIX systems.
- **D:** Shared memory avoids system calls for each read/write, making it efficient.


9. In POSIX shared memory, shm_open() returns: 
A. A pointer to the shared memory 
B. A file descriptor 
C. A memory-mapped address immediately 
D. A semaphore handle
**ANSWER: B**
**Explanation:**
- **B (Correct):** `shm_open()` returns a file descriptor, which can then be mapped into memory with `mmap()`.
- **A:** The pointer is obtained after mapping, not from `shm_open()`.
- **C:** Mapping is a separate step.
- **D:** Semaphores are unrelated to `shm_open()`.


10. A binary semaphore differs from a mutex because: 
A. Mutex is faster 
B. Binary semaphore can be signaled by a thread that didn’t acquire it 
C. Mutex can be acquired multiple times by the same thread without blocking 
D. Binary semaphore has no synchronization use
**ANSWER: B**
**Explanation:**
- **B (Correct):** Any thread can signal (release) a binary semaphore, but only the owner can unlock a mutex.
- **A:** Mutexes are not always faster.
- **C:** Mutexes are not reentrant by default; recursive mutexes are special.
- **D:** Binary semaphores are used for synchronization.


11. A counting semaphore initialized to N allows: 
A. Exactly N processes to be in the critical section simultaneously 
B. Unlimited processes in critical section 
C. At most N processes waiting 
D. Exactly N processes to wait
**ANSWER: A**
**Explanation:**
- **A (Correct):** The semaphore count represents the number of resources; up to N processes can enter before blocking.
- **B:** The number is limited to N, not unlimited.
- **C, D:** These refer to waiting, not entry.
**Example:**
If N=3, up to 3 processes can enter the section; the 4th will wait.


12. The P and V operations on semaphores: 
A. Increment and decrement the semaphore count respectively 
B. P → wait (decrement), V → signal (increment) 
C. Both increment 
D. Both decrement
**ANSWER: B**
**Explanation:**
- **B (Correct):** P (proberen) means wait (decrement), V (verhogen) means signal (increment).
- **A:** The order is reversed.
- **C, D:** Only one operation increments or decrements, not both.


13. Priority inversion in semaphore usage occurs when: 
A. Low priority thread holds semaphore needed by a high priority thread 
B. High priority thread holds semaphore needed by a low priority thread 
C. Semaphore count is negative 
D. Semaphore value overflows
**ANSWER: A**
**Explanation:**
- **A (Correct):** A low-priority thread blocks a high-priority thread by holding a needed resource.
- **B:** The reverse is not problematic.
- **C:** Semaphore counts are non-negative.
- **D:** Overflow is not the issue in priority inversion.
**Example:**
Mars Pathfinder mission experienced this problem; priority inheritance is a solution.


14. Which socket type guarantees message boundaries? 
A. TCP 
B. UDP 
C. RAW 
D. None of these
**ANSWER: B**
**Explanation:**
- **B (Correct):** UDP is message-oriented; each send/receive is a datagram.
- **A:** TCP is stream-oriented; message boundaries are not preserved.
- **C:** RAW sockets are for custom protocols, not for message boundaries.
- **D:** UDP does guarantee boundaries.


15. The listen() call in TCP server sockets: 
A. Actively starts connection establishment 
B. Passively waits for incoming connections 
C. Sends data to client 
D. Binds the socket to a port
**ANSWER: B**
**Explanation:**
- **B (Correct):** `listen()` puts the socket in a passive mode, waiting for connections.
- **A:** `connect()` is used for active connection.
- **C:** `send()` is for sending data.
- **D:** `bind()` binds the socket to a port.


16. In TCP, the TIME_WAIT state is important because: 
A. It reduces latency for new connections
B. It ensures old duplicate packets don’t get misinterpreted 
C. It frees ports quickly 
D. It’s only for servers
**ANSWER: B**
**Explanation:**
- **B (Correct):** TIME_WAIT ensures that delayed packets from an old connection are not mistaken for a new one.
- **A:** It actually delays port reuse.
- **C:** TIME_WAIT delays freeing ports.
- **D:** Both clients and servers can enter TIME_WAIT.


17. Which is NOT true about select()? 
A. Can monitor multiple sockets 
B. Blocks until at least one socket is ready or timeout expires 
C. Returns immediately if no socket is ready 
D. Can monitor sockets for read, write, and exceptions
**ANSWER: C**
**Explanation:**
- **C (Correct):** If no socket is ready, select() blocks (unless timeout=0).
- **A, B, D:** All are true: select() can monitor multiple sockets, block, and check for read/write/exception.


18. In preemptive scheduling: 
A. A running process cannot be interrupted 
B. Scheduler can stop a process to run another 
C. Only batch jobs run 
D. No context switch occurs
**ANSWER: B**
**Explanation:**
- **B (Correct):** Preemptive scheduling allows the OS to interrupt a running process to run another.
- **A:** This is non-preemptive scheduling.
- **C:** Preemptive scheduling is used for all job types.
- **D:** Context switches do occur.


19. Which scheduling algorithm may cause starvation? 
A. Round Robin 
B. Shortest Job First (SJF)
C. Priority Scheduling 
D. FCFS
**ANSWER: C**
**Explanation:**
- **C (Correct):** In priority scheduling, low-priority processes may never run if high-priority ones keep arriving.
- **A:** Round Robin gives each process a turn.
- **B:** SJF can also cause starvation, but the question asks for the most likely (priority scheduling is classic).
- **D:** FCFS (First Come First Serve) is fair.


20. Context switch time is: 
A. Counted as CPU utilization 
B. Pure overhead 
C. Always zero 
D. Negative in some cases
**ANSWER: B**
**Explanation:**
- **B (Correct):** Context switching does not do useful work; it is overhead.
- **A:** It is not counted as CPU utilization.
- **C:** It is never zero.
- **D:** It cannot be negative.


21. The scheduler that adjusts priorities dynamically to prevent starvation is: 
A. FCFS 
B. Multi-level feedback queue 
C. SJF 
D. Lottery scheduling
**ANSWER: B**
**Explanation:**
- **B (Correct):** Multi-level feedback queues can move jobs between queues to prevent starvation.
- **A, C, D:** These do not dynamically adjust priorities.


22. The main cost of handling a page fault is: 
A. OS scheduler run time 
B. Disk I/O to load the page
C. CPU cache invalidation 
D. TLB flush
**ANSWER: B**
**Explanation:**
- **B (Correct):** Loading a page from disk is much slower than other operations.
- **A, C, D:** These are minor compared to disk I/O.


23. In demand paging, the initial access to a large array: 
A. Has uniform access time for all elements 
B. May cause multiple page faults until the array is paged in 
C. Never causes page faults if array is allocated 
D. Causes only one page fault
**ANSWER: B**
**Explanation:**
- **B (Correct):** Each page of the array must be loaded as it is accessed, causing multiple faults.
- **A:** Access time is not uniform due to page faults.
- **C:** Allocation does not load pages.
- **D:** There can be many page faults, not just one.


24. Copy-on-write is used to: 
A. Avoid race conditions 
B. Delay copying of a page until it is modified
C. Improve mutex performance 
D. Avoid swapping
**ANSWER: B**
**Explanation:**
- **B (Correct):** Copy-on-write means the OS only copies a page when a write occurs, saving memory and time.
- **A, C, D:** These are unrelated to copy-on-write.
**Example:**
When a process forks, both share pages until one writes.


25. Thrashing occurs when: 
A. Disk is faster than memory 
B. Processes spend more time paging than executing 
C. The system has high CPU utilization 
D. Page size is too small
**ANSWER: B**
**Explanation:**
- **B (Correct):** Thrashing is when the system spends most of its time swapping pages in and out, not doing useful work.
- **A:** Disks are always slower than memory.
- **C:** CPU utilization drops during thrashing.
- **D:** Page size is not the main cause.


26. TLB miss penalty is reduced by: 
A. Increasing page size 
B. Increasing associativity of TLB 
C. Disabling demand paging 
D. Using FCFS scheduling
**ANSWER: B**
**Explanation:**
- **B (Correct):** Higher associativity means more entries can be searched, reducing misses.
- **A:** Larger pages do not reduce TLB misses.
- **C, D:** These are unrelated.


27. In producer-consumer using shared memory, semaphores must: 
A. Ensure mutual exclusion only 
B. Ensure synchronization and mutual exclusion 
C. Be avoided for performance 
D. Be replaced with sleep calls
**ANSWER: B**
**Explanation:**
- **B (Correct):** Semaphores are needed for both mutual exclusion (buffer access) and synchronization (empty/full slots).
- **A:** Only mutual exclusion is not enough.
- **C, D:** Avoiding semaphores or using sleep is incorrect and unsafe.


28. Non-blocking sockets are primarily useful for: 
A. Low-latency event-driven servers 
B. High-throughput batch jobs 
C. Reducing memory fragmentation 
D. Eliminating TIME_WAIT
**ANSWER: A**
**Explanation:**
- **A (Correct):** Non-blocking sockets allow servers to handle many connections without waiting for I/O.
- **B:** Batch jobs do not benefit from non-blocking I/O.
- **C, D:** These are unrelated.


29. The “dirty bit” in a page table entry is used to: 
A. Indicate page is in memory 
B. Indicate page has been modified since being loaded 
C. Indicate page has been swapped 
D. Indicate page is locked in memory
**ANSWER: B**
**Explanation:**
- **B (Correct):** The dirty bit is set when a page is written to, so the OS knows to write it back to disk.
- **A:** The present bit indicates if a page is in memory.
- **C:** There is no specific bit for swapped status.
- **D:** Locking is managed separately.


30. When two processes communicate via sockets on the same machine: 
A. Data always goes through the NIC 
B. OS may optimize by using loopback interface and kernel memory 
C. Performance is same as shared memory 
D. It’s not possible
**ANSWER: B**
**Explanation:**
- **B (Correct):** The OS can use the loopback interface and keep data in kernel memory, avoiding the network card (NIC).
- **A:** Data does not go through the NIC for local sockets.
- **C:** Shared memory is still faster than sockets.
- **D:** Local socket communication is possible and common.
