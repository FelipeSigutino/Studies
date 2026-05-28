public class Testing {



    static class Node {
        int data;
        Node next;
        Node (int newData) {
            this.data = newData;
            this.next = null;
        }
    }

    public void main(String[] args) {
        Node head = new Node(0);
        Node temp = head;
        for (int i = 0; i < 10; i++) {
            temp.next = new Node(i);
            temp = temp.next;
        }


        Node temp1 = head.next;
        while (temp1 != null) {
            System.out.print(temp1.data + " ");
            temp1 = temp1.next;

        }

    }
}
/*
go 10
ld doc1
link=a and link=b
write also link=c end finish.
eod
show
add d
show
rem a
remi 2
show
get 0
set 1 w
show
ch 1
ld doc2
eod
show
remi 0
index x
ha
*/