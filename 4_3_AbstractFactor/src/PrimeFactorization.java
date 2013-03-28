
public class PrimeFactorization {

	public static Set<Integer> get(int number) {
		Set<Integer> set = new NodeMultiSet<Integer>();
		
		for (int i = 2; i <= number; i++) {
			while (number % i == 0) {
				set.add(i);
				number /= i;
			}
		}
		return set;
	}
}
