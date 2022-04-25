package project3;

import java.util.NoSuchElementException;

import project3.MyList.Node;

public class myTest {

	public static void main(String[] args) {
		MyList<Integer> intList = new MyList<Integer>();
		intList.add(5,0);
		intList.add(4,1);
		intList.add(3,2);
		intList.add(2,3);
		intList.add(1,4);
		
		System.out.println(intList.SizeLinkedListRecursionW());
	}
}
		
//		MyList<Integer> intList = new MyList<Integer>();
//        System.out.println(intList.size());
//        System.out.println("Above should be 0, size of empty list.");
//        intList.add(5, 0);
//        System.out.println(intList);
//        System.out.println("Above should be \"5\", list with 1 object inside.");
//        System.out.println(intList.remove(0));
//        System.out.println("Above should be \"5\", removing object at [0].");
//
//        intList.add(5, 0);
//        intList.add(10, 1);
//        System.out.println("The list looks like: " + intList);
//        System.out.println(intList.size());
//        System.out.println("Above should be 2, size of list with stuff inside.");
//        System.out.println(intList.get(0));
//        System.out.println("Above should be 5, content at [0].");
//        System.out.println(intList.find(5));
//        System.out.println("Above should be 0, location of 5.");
//        System.out.println(intList.remove((Integer) 75));
//        System.out.println("Above should be null, trying to remove something that's not there.");
//        intList.clear();
//        System.out.println(intList.remove((Integer) 75));
//        System.out.println("Above should be null, trying to remove something from nothing.");
//        intList.clear(); // to test clearing empty list
//        System.out.println(intList);
//        System.out.println("Above should be an empty line, trying to print empty list.");
//        System.out.println(intList.size());
//        System.out.println("Above should be 0, size of empty list.");
//
//        try {
//            System.out.println(intList.get(7));
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println("Above should be an error, trying to get from invalid pos.");
//        try {
//            System.out.println(intList.add(5, 70));
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println("Above should be an error, trying to add to invalid pos.");
//        try {
//            System.out.println(intList.remove(75));
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println("Above should be an error, trying to remove from invalid pos.");
//        System.out.println(intList.find(5));
//        System.out.println("Above should be -1, testing find on empty list.");
//        System.out.println(intList.find(50));
//        System.out.println("Above should be -1, finding item not in list.");
//        intList.clear();
//        intList.add(5, 0);
//        System.out.println(intList.find(5));
//        System.out.println("Above should be 0, testing find on existing object list size 1");
//        System.out.println(intList.find(6));
//        System.out.println("Above should be -1, testing find on not existing object list size 1");
//        intList.add(7, 0);
//        intList.add(13, 0);
//        intList.add(24, 0);
//        System.out.println(intList.find(7));
//        System.out.println("Above should be 2, testing find in middle of list.");
//        intList.clear();
//        intList.add(10, 0);
//        intList.add(15, 1);
//        intList.add(20, 0);
//        intList.add(5, 3);
//        System.out.println("List looks like: " + intList);
//        System.out.println(intList.remove(3));
//        System.out.println("Above should be 5, remove from pos 3 of list len 4");
//        intList.add(5, 3);
//        System.out.println(intList.remove(2));
//        System.out.println("Above should be 15, remove from pos 2 of list len 4");
//        intList.add(15, 2);
//        System.out.println(intList.remove((Integer) 15));
//        System.out.println("Above should be 15, remove Object (Integer) 15 from pos 2 in list len" +
//                " 4");
//        intList.add(15, 2);
//        System.out.println(intList.remove((Integer) 5));
//        System.out.println("Above should be 5, remove Object (Integer) 15 from pos 3 in list len " +
//                "4");
//        intList.add(5, 3);
//        System.out.println(intList.remove((Integer) 20));
//        System.out.println("Above should be 20, remove Object (Integer) 20 from pos 0 in list len" +
//                " 4");
//
//        intList.clear();
//        intList.add(10, 0);
//        intList.add(15, 1);
//        intList.add(5, 0);
//        intList.add(5, 3);
//        System.out.println(intList);
//        System.out.println("Above should be 5, 10, 15, 5");
//        System.out.println(intList.get(0));
//        System.out.println("Above should be 5, get from pos 0");
//        System.out.println(intList.get(2));
//        System.out.println("Above should be 15, get from pos 2");
//
//        try {
//            System.out.println(intList.add(5, 10));
//            System.out.println(intList.get(7));
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println("Above should be error, trying to get from pos 7 in list size 4");
//        MyList<Integer> intList2 = new MyList<Integer>();
//        intList2.add(10, 0);
//        intList2.add(15, 1);
//        intList2.add(5, 0);
//        intList2.add(5, 3);
//        System.out.println("There's a new identical list: " + intList2);
//        System.out.println(intList.equals(intList2));
//        System.out.println("Above should be true, equals on 2 identical lists.");
//        intList2.remove(0);
//        System.out.println(intList.equals(intList2));
//        System.out.println("Above should be false, equals on 2 different lists, a.len > b.len.");
//        intList2.add(5, 0);
//        intList2.add(5, 0);
//        System.out.println(intList.equals(intList2));
//        System.out.println("Above should be false, equals on 2 different lists, a.len < b.len.");
//        intList.clear();
//        intList2.clear();
//        intList.add(5, 0);
//        intList2.add(5, 0);
//        intList.add(8, 0);
//        intList2.add(7, 0);
//        intList.add(5, 0);
//        intList2.add(5, 0);
//        System.out.println(intList.equals(intList2));
//        System.out.println("Above should be false, equals on 2 different lists, a.len = b.len.");
//        MyList<String> strList = new MyList<String>();
//        System.out.println(intList.equals(strList));
//        System.out.println("Above should be false, depending on your implementation");
//        intList.clear();
//        System.out.println(intList.equals(strList));
//        System.out.println("Above can be true or false, depending on your implementation");
//        System.out.println("-----------------------STACK");
//        // STACK TESTING
//        MyStack<Integer> intStack = new MyStack<>();
//        System.out.println(intStack);
//        System.out.println("Above should be empty line");
//        try {
//            intStack.push(null);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println("Above should be error, pushing null");
//        intStack.push(4);
//        intStack.push(5);
//        System.out.println(intStack);
//        System.out.println("Above should be 4, 5.");
//        System.out.println(intStack.top());
//        System.out.println(intStack.pop());
//        System.out.println("Last 2 numbers should be 5 and 5, testing top and pop.");
//        intStack.push(5);
//        intStack.push(6);
//        while (intStack.top() != null) {
//            intStack.pop();
//        }
//        System.out.println(intStack.pop());
//        System.out.println(intStack.top());
//        System.out.println("Should print null and null, testing top and pop on empty stack");
//        MyStack<Integer> intStack2 = new MyStack<>();
//        System.out.println(intStack.equals(intStack2));
//        System.out.println("Should print true, testing equals on 2 empty stacks");
//
//        intStack2.push(4);
//        System.out.println(intStack.equals(intStack2));
//        System.out.println("Should print false, testing equals on 1 empty stacks");
//        intStack.push(4);
//        intStack.push(5);
//        intStack2.push(5);
//        System.out.println(intStack.equals(intStack2));
//        System.out.println("Should print true, testing equals on identical stacks.");
//        intStack2.push(6);
//        intStack.push(6);
//        intStack.push(7);
//        System.out.println(intStack.equals(intStack2));
//        System.out.println("Should print false, testing equals on stack where a.len > b.len.");
//        intStack2.push(7);
//        intStack2.push(8);
//        System.out.println(intStack.equals(intStack2));
//        System.out.println("Should print false, testing equals on stack where a.len < b.len.");
//        intStack.push(9);
//        System.out.println(intStack.equals(intStack2));
//        System.out.println("Should print false, testing equals on stack where a.len = b.len.");
//        MyStack<String> strStack = new MyStack<>();
//        strStack.push("test");
//        System.out.println(intStack.equals(strStack));
//        System.out.println("Should print false, testing equals on stacks with diff types.");
//
//     
//        MyQueue<Integer> intQueue = new MyQueue<>();
//        intQueue.enqueue(1);
//        System.out.println(intQueue.dequeue());
//        System.out.println("If printed 1, successfully added and removed.");        
//        intQueue.enqueue(1);
//        intQueue.enqueue(2);
//        intQueue.enqueue(3);
//        System.out.print(intQueue.dequeue());
//        System.out.print(intQueue.dequeue());
//        System.out.println(intQueue.dequeue());
//        System.out.println("If printed 1 2 3, successfully added and removed 3 objs.");
//        System.out.println("If printed 1, then 1 2 3, passed normal enqueue, dequeue testcases.");
//        System.out.println(intQueue.dequeue());
//        System.out.println("If printed null, passed empty dequeue testcase.");
//        try {
//            intQueue.enqueue(null);
//        } catch (Exception ignored){}
//        intQueue.enqueue(1);
//        System.out.println(intQueue.peek());
//        System.out.println("If printed 1, passed normal peek testcases.");
//        System.out.print(intQueue.dequeue());
//        System.out.println(intQueue.peek());
//        System.out.println("If printed 1 then null, passed empty peek testcase.");       
//        MyQueue<Integer> intQueue2 = new MyQueue<>();
//        intQueue.enqueue(1);
//        intQueue.enqueue(2);
//        intQueue.enqueue(3);       
//        intQueue2.enqueue(1);
//        intQueue2.enqueue(2);
//        intQueue2.enqueue(3);
//
//        System.out.println(intQueue.equals(intQueue2));
//        System.out.println("If printed true, passed normal equals testcase.");
//        intQueue.enqueue(4);
//        System.out.println(intQueue.equals(intQueue2));
//        System.out.println("If printed false, passed a.size > b.size not equals testcase.");
//        intQueue2.enqueue(5);
//        intQueue2.enqueue(6);
//        System.out.println(intQueue.equals(intQueue2));
//        System.out.println("If printed false, passed a.size < b.size not equals testcase.");
//        
//        
//        intQueue2.dequeue();
//        System.out.println(intQueue.equals(intQueue2));
//        System.out.println("If printed false, passed a.size == b.size not equals testcase.");
//        System.out.println(intQueue);
//        System.out.println(intQueue2);
//        System.out.println("Two queues should be 1, 2, 3, 4 and 2, 3, 5, 6.");
//        for (int i = 0; i < 16; i += 1) {
//            intQueue2.enqueue(i);
//        }
//        System.out.println(intQueue2);
//        System.out.println("Queue should be  2, 3, 5, 6, 0 ... 15, testing grow function " +
//                "given starting size is 10.");
//        for (int i = 0; i < 16; i += 1) {
//            intQueue2.dequeue();
//        }
//        for (int i = 0; i < 16; i += 1) {
//            intQueue2.enqueue(i);
//        }
//        for (int i = 0; i < 16; i += 1) {
//            intQueue2.dequeue();
//        }
//
//        System.out.println(intQueue2);
//        System.out.println("Queue should be 12, 13, 14, 15.");
//
//        for (int i = 0; i < 50; i += 1) {
//            intQueue2.enqueue(i);
//        }
//
//        System.out.println("Testing \"wrapped\" queues");
//        MyQueue<Integer> intQueue3 = new MyQueue<>();
//        MyQueue<Integer> intQueue4 = new MyQueue<>();
//        for (int i = 0; i < 13; i += 1) {
//            intQueue3.enqueue(i);
//            intQueue4.enqueue(i);
//        }
//        for (int i = 0; i < 7; i += 1) {
//            intQueue3.dequeue();
//            intQueue4.dequeue();
//        }
//        for (int i = 0; i < 13; i += 1) {
//            intQueue3.enqueue(i + 13);
//            intQueue4.enqueue(i + 13);
//        }
//        MyQueue<Integer> intQueue5 = new MyQueue<>();
//        for (int i = 0; i < 6; i += 1) {
//            intQueue5.enqueue(i);
//        }
//        MyQueue<Integer> intQueue6 = new MyQueue<>();
//        for (int i = 0; i < 19; i += 1) {
//            intQueue6.enqueue(i + 7);
//        }
//        System.out.println(intQueue3.equals(intQueue4));
//        System.out.println("Above should be true, two identical wrapped Queues");
//        System.out.println(intQueue3.equals(intQueue5));
//        System.out.println("Above should be false, a wraps, b doesnt");
//        System.out.println(intQueue5.equals(intQueue4));
//        System.out.println("Above should be false, b wraps, a doesnt");
//        System.out.println(intQueue3.equals(intQueue6));
//        System.out.println("Above should be true, a wraps, b doesnt");
//        System.out.println(intQueue6.equals(intQueue4));
//        System.out.println("Above should be true, b wraps, a doesnt");
