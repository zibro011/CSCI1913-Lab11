//  OBSERVATION DEQUE. Test the class DEQUE. 40 points total.

class ObservationDeque
{

//  MAIN. Test the DEQUE on various example arguments.

  public static void main(String [] args)
  {
    Deque<String> deque = new Deque<String>();

    System.out.println(deque.isEmpty());       // true                2 points.

    try
    {
      System.out.println(deque.dequeueFront());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No dequeueFront.");  //  No dequeueFront.   2 points.
    }

    try
    {
      System.out.println(deque.dequeueRear());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No dequeueRear.");   //  No dequeueRear.    2 points.
    }

//  Enqueueing to the rear and dequeueing from the rear makes the DEQUE act
//  like a stack.

    deque.enqueueRear("A");
    deque.enqueueRear("B");
    deque.enqueueRear("C");

    System.out.println(deque.isEmpty());       //  false              2 points.

    System.out.println(deque.dequeueRear());   //  C                  2 points.
    System.out.println(deque.dequeueRear());   //  B                  2 points.
    System.out.println(deque.dequeueRear());   //  A                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the rear and dequeueing from the front makes the DEQUE act
//  like a queue.

    deque.enqueueRear("A");
    deque.enqueueRear("B");
    deque.enqueueRear("C");

    System.out.println(deque.dequeueFront());  //  A                  2 points.
    System.out.println(deque.dequeueFront());  //  B                  2 points.
    System.out.println(deque.dequeueFront());  //  C                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the front makes the DEQUE act
//  like a stack.

    deque.enqueueFront("A");
    deque.enqueueFront("B");
    deque.enqueueFront("C");

    System.out.println(deque.dequeueFront());  //  C                  2 points.
    System.out.println(deque.dequeueFront());  //  B                  2 points.
    System.out.println(deque.dequeueFront());  //  A                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the rear makes the DEQUE act
//  like a queue.

    deque.enqueueFront("A");
    deque.enqueueFront("B");
    deque.enqueueFront("C");

    System.out.println(deque.dequeueRear());   //  A                  2 points.
    System.out.println(deque.dequeueRear());   //  B                  2 points.
    System.out.println(deque.dequeueRear());   //  C                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.
  }
}

class Deque<Base>
  {
    private Node head;
    
    private class Node
    {
      private Base object;
      private Node left;
      private Node right;
      private Node(Base object, Node left, Node right)
      {
        this.object = object;
        this.left = left;
        this.right = right;
      }
    }
    
    public Deque()
    {
      head = new Node (null, null, null);
      head.right = head;
      head.left = head;
    }
    
    public void enqueueFront (Base object)
    {
      //cant use special case - both enqueue methods are wrong rn
      Node temp = new Node (object, head, head.right);
      head.right = temp;
      head.right.right.left = temp;
    }
    public void enqueueRear (Base object) //this doesn't work i think
    {
      //cant use special case
      Node temp = new Node (object, head.left, head);
      head.left = temp;
      head.left.left.right = temp;
    }
    public Base dequeueFront() 
    {
      if(isEmpty())
      {
        throw new IllegalStateException("deque is empty");
      }
      else
      {
        Base temp = head.right.object;
        head.right.right.left = head;
        head.right = head.right.right;
        return temp;
      }
    }
    public Base dequeueRear()
    {
      if(isEmpty())
      {
        throw new IllegalStateException("deque is empty");
      }
      else
      {
        Base temp = head.left.object;
        head.left.left.right = head;
        head.left = head.left.left;
        return temp;
      }
    }
    public boolean isEmpty()
    {
      return head.right==head && head.left==head;
    }
  }
