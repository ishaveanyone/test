package com.dist.jdksource.util.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.Collection;

/**
 * An implementation of {@link ReadWriteLock} supporting similar
 * semantics to {@link ReentrantLock}.
 * <p>This class has the following properties:
 *
 * <ul>
 * <li><b>Acquisition order</b>
 *
 * <p>This class does not impose a reader or writer preference
 * ordering for lock access.  However, it does support an optional
 * <em>fairness</em> policy.
 *
 * <dl>
 * <dt><b><i>Non-fair mode (default)</i></b>
 * <dd>When constructed as non-fair (the default), the order of entry
 * to the read and write lock is unspecified, subject to reentrancy
 * constraints.  A nonfair lock that is continuously contended may
 * indefinitely postpone one or more reader or writer threads, but
 * will normally have higher throughput than a fair lock.
 *
 * <dt><b><i>Fair mode</i></b>
 * <dd>When constructed as fair, threads contend for entry using an
 * approximately arrival-order policy. When the currently held lock
 * is released, either the longest-waiting single writer thread will
 * be assigned the write lock, or if there is a group of reader threads
 * waiting longer than all waiting writer threads, that group will be
 * assigned the read lock.
 *
 * <p>A thread that tries to acquire a fair read lock (non-reentrantly)
 * will block if either the write lock is held, or there is a waiting
 * writer thread. The thread will not acquire the read lock until
 * after the oldest currently waiting writer thread has acquired and
 * released the write lock. Of course, if a waiting writer abandons
 * its wait, leaving one or more reader threads as the longest waiters
 * in the queue with the write lock free, then those readers will be
 * assigned the read lock.
 *
 * <p>A thread that tries to acquire a fair write lock (non-reentrantly)
 * will block unless both the read lock and write lock are free (which
 * implies there are no waiting threads).  (Note that the non-blocking
 * {@link ReadLock#tryLock()} and {@link WriteLock#tryLock()} methods
 * do not honor this fair setting and will immediately acquire the lock
 * if it is possible, regardless of waiting threads.)
 * <p>
 * </dl>
 *
 * <li><b>Reentrancy</b>
 *
 * <p>This lock allows both readers and writers to reacquire read or
 * write locks in the style of a {@link ReentrantLock}. Non-reentrant
 * readers are not allowed until all write locks held by the writing
 * thread have been released.
 *
 * <p>Additionally, a writer can acquire the read lock, but not
 * vice-versa.  Among other applications, reentrancy can be useful
 * when write locks are held during calls or callbacks to methods that
 * perform reads under read locks.  If a reader tries to acquire the
 * write lock it will never succeed.
 *
 * <li><b>Lock downgrading</b>
 * <p>Reentrancy also allows downgrading from the write lock to a read lock,
 * by acquiring the write lock, then the read lock and then releasing the
 * write lock. However, upgrading from a read lock to the write lock is
 * <b>not</b> possible.
 *
 * <li><b>Interruption of lock acquisition</b>
 * <p>The read lock and write lock both support interruption during lock
 * acquisition.
 *
 * <li><b>{@link Condition} support</b>
 * <p>The write lock provides a {@link Condition} implementation that
 * behaves in the same way, with respect to the write lock, as the
 * {@link Condition} implementation provided by
 * {@link ReentrantLock#newCondition} does for {@link ReentrantLock}.
 * This {@link Condition} can, of course, only be used with the write lock.
 *
 * <p>The read lock does not support a {@link Condition} and
 * {@code readLock().newCondition()} throws
 * {@code UnsupportedOperationException}.
 *
 * <li><b>Instrumentation</b>
 * <p>This class supports methods to determine whether locks
 * are held or contended. These methods are designed for monitoring
 * system state, not for synchronization control.
 * </ul>
 *
 * <p>Serialization of this class behaves in the same way as built-in
 * locks: a deserialized lock is in the unlocked state, regardless of
 * its state when serialized.
 *
 * <p><b>Sample usages</b>. Here is a code sketch showing how to perform
 * lock downgrading after updating a cache (exception handling is
 * particularly tricky when handling multiple locks in a non-nested
 * fashion):
 *

{@link ReadWriteLock}的实现，支持与{@link ReentrantLock}类似的语义。
 * <p>此类具有以下属性：
 *
 * <ul>
 * <li> <b>采购订单</ b>
 *
 * <p>此类不会强制执行锁定访问的读者或编写者首选项顺序。但是，它确实支持可选的<em>公平性</ em>策略。
 *
 * <dl>
 * <dt> <b> <i>非公平模式（默认）</ i> </ b>
 * <dd>当构造为非公平（默认）时，输入到读写锁定的顺序是未指定的，受重入约束的限制。持续争用的非公平锁定可能无限期地推迟一个或多个读取器或写入器线程，但通常具有比公平锁定更高的吞吐量。
 *
 * <dt> <b> <i>合理模式</ i> </ b>
 * <dd>当构建为公平时，线程使用近似到达顺序策略争用进入。释放当前保持的锁定时，将为最长等待的单个写入器线程分配写入锁定，或者如果有一组读取器线程等待的时间超过所有等待的写入器线程，则将为该组分配读取锁定。
 *
 * <p>如果保持写锁定或者有等待的写入程序线程，则尝试获取公平读锁定（非重复）的线程将阻塞。在最旧的当前等待的写入器线程获取并释放写锁定之前，线程将不会获取读锁定。当然，如果等待的写入者放弃其等待，将一个或多个读取器线程作为队列中最长的服务器并且写锁定空闲，那么将为这些读取器分配读锁定。
 *
 * <p>尝试获取公平写锁定（非重复）的线程将阻塞，除非读锁定和写锁定都是空闲的（这意味着没有等待的线程）。 （请注意，非阻塞{@link ReadLock＃tryLock（）}和{@link WriteLock＃tryLock（）}方法不遵循此公平设置，并且如果可能，将立即获取锁定，无论等待线程如何。）
 * <p>
 * </ dl>
 *
 * <li> <b>可重入</ b>
 *
 * <p>此锁允许读者和作者以{@link ReentrantLock}的方式重新获取读或写锁。在写入线程持有的所有写锁定都被释放之前，不允许使用非重入读取器。
 *
 * <p>此外，编写器可以获取读锁定，但反之亦然。在其他应用程序中，在调用期间保持写锁定或在读取锁定下执行读取的方法的回调时，重入可能很有用。如果读者试图获取写锁定，它将永远不会成功。
 *
 * <li> <b>锁定降级</ b>
 * <p> Reentrancy还允许通过获取写锁定，然后读取锁定然后释放写入锁定，从写入锁定降级到读取锁定。但是，从读锁定升级到写锁定<b>不可能</ b>。
 *
 * <li> <b>锁定获取中断</ b>
 * <p>读取锁定和写入锁定都支持锁定获取期间的中断。
 *
 * <li> <b> {@ link Condition}支持</ b>
 * <p>写锁定提供{@link Condition}实现，就写锁而言，行为方式相同，因为{@link ReentrantLock＃newCondition}提供的{@link Condition}实现为{@链接ReentrantLock}。
 *此{@link Condition}当然只能与写锁一起使用。
 *
 * <p>读锁定不支持{@link Condition}和
 * {@code readLock（）。newCondition（）}抛出
 * {@code UnsupportedOperationException}。
 *
 * <li> <b>仪表</ b>
 * <p>此类支持确定锁是保持还是争用的方法。这些方法用于监视系统状态，而不是用于同步控制。
 * </ ul>
 *
 * <p>此类的序列化与内置锁的行为方式相同：反序列化锁处于解锁状态，无论序列化时的状态如何。
 *
 * <p> <b>示例用法</ b>。下面是一个代码草图，展示了如何在更新缓存后执行锁定降级（在以非嵌套方式处理多个锁时，异常处理尤其棘手）：




 * <pre> {@code
 * class CachedData {
 *   Object data;
 *   volatile boolean cacheValid;
 *   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 *
 *   void processCachedData() {
 *     rwl.readLock().lock();
 *     if (!cacheValid) {
 *       // Must release read lock before acquiring write lock
 *       rwl.readLock().unlock();
 *       rwl.writeLock().lock();
 *       try {
 *         // Recheck state because another thread might have
 *         // acquired write lock and changed state before we did.
 *         if (!cacheValid) {
 *           data = ...
 *           cacheValid = true;
 *         }
 *         // Downgrade by acquiring read lock before releasing write lock
 *         rwl.readLock().lock();
 *       } finally {
 *         rwl.writeLock().unlock(); // Unlock write, still hold read
 *       }
 *     }
 *
 *     try {
 *       use(data);
 *     } finally {
 *       rwl.readLock().unlock();
 *     }
 *   }
 * }}</pre>
 *
 * ReentrantReadWriteLocks can be used to improve concurrency in some
 * uses of some kinds of Collections. This is typically worthwhile
 * only when the collections are expected to be large, accessed by
 * more reader threads than writer threads, and entail operations with
 * overhead that outweighs synchronization overhead. For example, here
 * is a class using a TreeMap that is expected to be large and
 * concurrently accessed.
 *
 *  <pre> {@code
 * class RWDictionary {
 *   private final Map<String, Data> m = new TreeMap<String, Data>();
 *   private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 *   private final Lock r = rwl.readLock();
 *   private final Lock w = rwl.writeLock();
 *
 *   public Data get(String key) {
 *     r.lock();
 *     try { return m.get(key); }
 *     finally { r.unlock(); }
 *   }
 *   public String[] allKeys() {
 *     r.lock();
 *     try { return m.keySet().toArray(); }
 *     finally { r.unlock(); }
 *   }
 *   public Data put(String key, Data value) {
 *     w.lock();
 *     try { return m.put(key, value); }
 *     finally { w.unlock(); }
 *   }
 *   public void clear() {
 *     w.lock();
 *     try { m.clear(); }
 *     finally { w.unlock(); }
 *   }
 * }}</pre>
 *
 * <h3>Implementation Notes</h3>
 *
 * <p>This lock supports a maximum of 65535 recursive write locks
 * and 65535 read locks. Attempts to exceed these limits result in
 * {@link Error} throws from locking methods.
 *
 * @since 1.5
 * @author Doug Lea
 */

