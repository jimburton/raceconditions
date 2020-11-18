# Race conditions

Lab session for CI646.

There are two main types of race condition that are commonly encountered
when creating a multi-threaded application. These are *Check-Then-Act* and 
*Read-Modify-Write*.

## Check-Then-Act

This occurs when several thread needs to check some condition, such as reading a numeric value and
comparing it to another, then act according to the result of the check.

For instance, we may have a critical section in which each thread checks the number of widgets
available in our online shop, `numWidgets`. If we have run out of widgets, `numWidgets == 0`, 
we should order some more from our supplier. A scenario where this could go wrong:

+ *Thread A* reads the value and finds that `numWidgets == 0`.
+ *Thread A* is interrupted and *Thread B* takes over.
+ *Thread B* reads the value and finds that `numWidgets == 0`.
+ *Thread B* orders more widgets from the supplier.
+ *Thread B* is interrupted and *Thread A* takes over.
+ *Thread A* orders more widgets from the supplier.

We've overspent our Widget budget and the shop is going bust :(

The class `CheckThenAct` is meant to manage the value of a number but it only makes one change, 
setting the number to -1 if its value is zero. Logically, the number should only need to be changed 
once. Run the `main` method in `CheckThenAct` and see whether this is the case.

Note that we could be very unlikely to enter the race condition here apart from the fact that we
call `Thread.sleep(long milliseconds)` inside the critical section. However, it *might* happen even
without simulating an interruption -- the important thing is we are *not guaranteed* to avoid the race 
condition. 

Use the `synchronized` keyword to make the `changeNumber` method thread-safe and fix the race 
condition.

We should be as conservative as possible when choosing what to lock; it could be quite wasteful to 
lock an entire method. Read about the Java class 
[`AtomicInteger`](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/atomic/AtomicInteger.html)
and its `compareAndSet` method. Replace the `int` with an `AtomicInteger` which is initialised 
with a value of zero. Remove the `synchronised` keyword and let `AtomicInteger` take care of the 
problem.

## Read-Modify-Write

The next type of race condition is a variation on the first. It can occur whenever we need to:

+ Fetch a value from a field.
+ Modify the value.
+ Store the new value to the field.

The problems arise when two interleaved threads overwrite each other's modifications. Run 
the `main` method in the class `ReadModifyWrite`. Note that you need to wait 5 seconds for the method to complete,
and the result isn't what we hoped for. What is the critical section in this code?

Again, we drew attention to the lack of thread-safety by simulating an interruption in the critical section, but 
there is no guarantee that it wouldn't happen even without that. `AtomicInteger` has a method that will solve the 
problem -- which one is it?

## Bank accounts and ATMs

Have a look at the code in the `bank` package and run the `main` method in the `ATM` class. See if you can work out how 
to make it thread safe. Remember that using `synchronized` to lock a method may or may not prevent all access
to the underlying variables (e.g. the balance in an account).
