import java.util.HashMap;

public class LRU {

	public static void main(String[] args) {
		LRUClass lru = new LRUClass(3);
		lru.put(1, 3);
		lru.put(2, 4);
		lru.put(3, 6);
		System.out.println(lru.get(3));
	}

	static class LRUClass {
		Node head;
		Node tail;
		HashMap<Integer, Node> map;
		int size;

		public LRUClass(int size) {
			// TODO Auto-generated constructor stub
			head = new Node(0, 0);
			tail = new Node(0, 0);
			head.next = tail;
			tail.prev = head;
			map = new HashMap<Integer, Node>();
			this.size = size;
		}

		void put(int key, int val) {
			if (map.containsKey(key)) {
				remove(map.get(key));
			}

			if (map.size() == size) {
				remove(tail.prev);
			}

			insert(new Node(key, val));
		}

		void insert(Node curr) {
			map.put(curr.key, curr);
			Node headnext = head.next;
			head.next = curr;
			curr.prev = head;
			headnext.prev = curr;
			curr.next = headnext;
		}

		int get(int key) {
			if (!map.containsKey(key)) {
				return -1;
			} else {
				Node node = map.get(key);
				System.out.println(node);
				remove(node);
				insert(node);
				return node.val;
			}
		}

		void remove(Node node) {
			System.out.println(node);
			map.remove(node.key);
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}

		static class Node {
			Node prev;
			Node next;
			int key;
			int val;

			public Node(int key, int val) {
				// TODO Auto-generated constructor stub
				this.key = key;
				this.val = val;
				this.prev = null;
				this.next = null;
			}
		}
	}

}
