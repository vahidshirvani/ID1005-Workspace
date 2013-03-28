import java.util.Scanner;


public class UseAbstractFactor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		//AbstractFactor factor = new HashFactor(10000);
		AbstractFactor factor = new BinFactor(1000);
		
		System.out.println("Input pattern should be like # / # were # is an integer");
		Scanner in = new Scanner(System.in);
		String numerator = in.next();
		in.next();
		String denominator = in.next();
		
		Set<Integer> factorsOfNum = factor.get(new Integer(numerator));
		Set<Integer> factorsOfDen = factor.get(new Integer(denominator));
		
		Set<Integer> factorOfNumSimplified = factorsOfNum.difference(factorsOfDen);
		Set<Integer> factorOfDenSimplified = factorsOfDen.difference(factorsOfNum);
		
		int productOfNum = 1;
		for(Integer number: factorOfNumSimplified)
			productOfNum *= number;
		
		int productOfDen = 1;
		for(Integer number: factorOfDenSimplified)
			productOfDen *= number;
		
		System.out.println(	  numerator             + " / " + denominator           + " = " +
							  factorsOfNum          + " / " + factorsOfDen          + " = " +
							  factorOfNumSimplified + " / " + factorOfDenSimplified + " = " + 
							  productOfNum          + " / " + productOfDen);
	}
}
