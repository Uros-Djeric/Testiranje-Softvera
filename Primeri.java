package testiranje;

public class Primeri {
	public static int sumN(int[] x, int n){
	    //Effects:
	    // if x==null throw NullPointerException
	    // else return the sum of the first „n“
	    // elements in x
	    int sum = 0;
	    if (n>= x.length)
	          n = x.length-1;
	    for (int i = 0; i < n; i++){
	          sum+=x[i];
	    }
	    return sum;
	}
	
	public static int oddOrPos(int[] x) {
		   //Effects: if x==null throw NullPointerException
		   // else return the number of elements in x that
		   // are either odd or positive (or both)
		   int count = 0;
		   for (int i = 0; i < x.length; i++) {
		      if (x[i]%2 == 1 || x[i] > 0) {
		         count++;
		      }
		   }
		   return count;
	}

	public static void main(String[] args) {
		System.out.println(sumN(new int[] {1,2,3}, 2));
		System.out.println(oddOrPos(new int[] {-2,1,2,3}));
	}

}
