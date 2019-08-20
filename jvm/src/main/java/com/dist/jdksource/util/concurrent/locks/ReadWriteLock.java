package com.dist.jdksource.util.concurrent.locks;

/**
 * A {@code ReadWriteLock} maintains a pair of associated {@link
 * Lock locks}, one for read-only operations and one for writing.
 * The {@link #readLock read lock} may be held simultaneously by
 * multiple reader threads, so long as there are no writers.  The
 * {@link #writeLock write lock} is exclusive.
 *
 * <p>All {@code ReadWriteLock} implementations must guarantee that
 * the memory synchronization effects of {@code writeLock} operations
 * (as specified in the {@link Lock} interface) also hold with respect
 * to the associated {@code readLock}. That is, a thread successfully
 * acquiring the read lock will see all updates made upon previous
 * release of the write lock.
 *
 * <p>A read-write lock allows for a greater level of concurrency in
 * accessing shared data than that permitted by a mutual exclusion lock.
 * It exploits the fact that while only a single thread at a time (a
 * <em>writer</em> thread) can modify the shared data, in many cases any
 * number of threads can concurrently read the data (hence <em>reader</em>
 * threads).
 * In theory, the increase in concurrency permitted by the use of a read-write
 * lock will lead to performance improvements over the use of a mutual
 * exclusion lock. In practice this increase in concurrency will only be fully
 * realized on a multi-processor, and then only if the access patterns for
 * the shared data are suitable.
 *
 * <p>Whether or not a read-write lock will improve performance over the use
 * of a mutual exclusion lock depends on the frequency that the data is
 * read compared to being modified, the duration of the read and write
 * operations, and the contention for the data - that is, the number of
 * threads that will try to read or write the data at the same time.
 * For example, a collection that is initially populated with data and
 * thereafter infrequently modified, while being frequently searched
 * (such as a directory of some kind) is an ideal candidate for the use of
 * a read-write lock. However, if updates become frequent then the data
 * spends most of its time being exclusively locked and there is little, if any
 * increase in concurrency. Further, if the read operations are too short
 * the overhead of the read-write lock implementation (which is inherently
 * more complex than a mutual exclusion lock) can dominate the execution
 * cost, particularly as many read-write lock implementations still serialize
 * all threads through a small section of code. Ultimately, only profiling
 * and measurement will establish whether the use of a read-write lock is
 * suitable for your application.
 *
 *
 * <p>Although the basic operation of a read-write lock is straight-forward,
 * there are many policy decisions that an implementation must make, which
 * may affect the effectiveness of the read-write lock in a given application.
 * Examples of these policies include:
 * <ul>
 * <li>Determining whether to grant the read lock or the write lock, when
 * both readers and writers are waiting, at the time that a writer releases
 * the write lock. Writer preference is common, as writes are expected to be
 * short and infrequent. Reader preference is less common as it can lead to
 * lengthy delays for a write if the readers are frequent and long-lived as
 * expected. Fair, or &quot;in-order&quot; implementations are also possible.
 *
 * <li>Determining whether readers that request the read lock while a
 * reader is active and a writer is waiting, are granted the read lock.
 * Preference to the reader can delay the writer indefinitely, while
 * preference to the writer can reduce the potential for concurrency.
 *
 * <li>Determining whether the locks are reentrant: can a thread with the
 * write lock reacquire it? Can it acquire a read lock while holding the
 * write lock? Is the read lock itself reentrant?
 *
 * <li>Can the write lock be downgraded to a read lock without allowing
 * an intervening writer? Can a read lock be upgraded to a write lock,
 * in preference to other waiting readers or writers?
 *
 * </ul>
 * You should consider all of these things when evaluating the suitability
 * of a given implementation for your application.
 *
 * @see ReentrantReadWriteLock
 * @see Lock
 * @see ReentrantLock
 *
 * @since 1.5
 * @author Doug Lea



@code ReadWriteLock}维护一对关联的{@link
 *锁定}，一个用于只读操作，另一个用于写入。
 * {@link #readLock read lock}可以同时保持
 *多个读者线程，只要没有编写者。 {@link #writeLock写锁定}是独占的。
 *
 * <p>所有{@code ReadWriteLock}实现必须保证
 * {@code writeLock}操作的内存同步效果
 *（在{@link Lock}界面中指定）也尊重
 *到相关的{@code readLock}。也就是说，一个线程成功了
 *获取读锁定将看到之前所做的所有更新
 *释放写锁。
 *
<p>读取锁定允许访问共享数据的并发性高于互斥锁定所允许的并发性。
 *它利用了这样一个事实，即一次只有一个线程（a
 * <em> writer </ em>线程）可以修改共享数据，在很多情况下都可以
 *线程数可以同时读取数据（因此<em> reader </ em>
 *线程）。
 *理论上，使用读写锁所允许的并发性的增加将导致相对于使用互斥锁的性能提高。实际上，这种并发性的增加只能在多处理器上完全实现，并且只有在共享数据的访问模式合适时才能实现。
 *
 * <p>读写锁是否会提高使用互斥锁的性能取决于与被修改相比读取数据的频率，读写操作的持续时间以及争用数据 - 即尝试同时读取或写入数据的线程数。
 *例如，最初填充数据并且之后不经常修改但经常搜索的集合（例如某种目录）是使用读写锁的理想候选者。但是，如果更新变得频繁，那么数据的大部分时间都会被完全锁定，并且并发性几乎没有增加。此外，如果读取操作太短，则读写锁定实现的开销（其本质上比互斥锁更复杂）可以支配执行成本，特别是因为许多读写锁实现仍然通过序列化所有线程。小部分代码。最终，只有分析和测量才能确定使用读写锁是否适合您的应用。
 *
 *
 * <p>虽然读写锁的基本操作是直接的，但实现必须做出许多策略决策，这可能会影响给定应用程序中读写锁的有效性。
 *这些政策的示例包括：<ul>
 * <li>在写入器释放写入锁定时，确定在读取器和写入器都在等待时是否授予读锁定或写入锁定。写作者偏好很常见，因为写入预计很短且很少发生。读者偏好不太常见，因为如果读者频繁且长期按预期，它可能导致写入的长时间延迟。公平的，或“有序的”实现也是可能的。
 * <li>确定读取器处于活动状态且写入程序正在等待时是否请求读取锁定的读取器被授予读取锁定。
 *读者的偏好可以无限期地延迟写入者，而对编写者的偏好可以减少并发的可能性。
 *
 * <li>确定锁是否可重入：具有写锁的线程是否可以重新获取它？它可以在保持写锁定的同时获得读锁定吗？读锁本身是否可重入？
 *
 * <li>写锁可以降级为读锁而不允许介入的写入程序吗？读锁可以升级到写锁，优先于其他等待的读者或编写者吗？
 *
 * </ ul>
 *在评估给定实现对您的应用程序的适用性时，您应该考虑所有这些事情。
 *
 * @see ReentrantReadWriteLock
 * @see Lock
 * @see ReentrantLock
 *
 * @since 1.5
 * @author Doug Lea

 综上所述 在大量读取的情况下使用读写锁 会得到很大的执行效率
 */

 public interface ReadWriteLock {
    /**
     * Returns the lock used for reading.
     *
     * @return the lock used for reading
     */
    Lock readLock();

    /**
     * Returns the lock used for writing.
     *
     * @return the lock used for writing
     */
    Lock writeLock();
}
