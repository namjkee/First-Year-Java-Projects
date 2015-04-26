
public class GeometricMean {
	
	public static float calculate(String data) {
		if (data.contains("-1") == true){
			String[] data2 = data.split("\\s");
			int numOfK = 0;
			float product = 1;
			
			for (int i = 0; i < data2.length; i++) {
				
				if (data2[i].contentEquals("-1")) {
					break;
				}
				
				int k = Integer.parseInt(data2[i]);
				numOfK += 1; // add each value that's before -1
				product = product * k;
			}
			float exponential = (float) 1/numOfK;
			float geoMean = (float) (Math.pow(product, exponential));
			geoMean = Math.round(geoMean * 1000)/1000.0f;
			System.out.println(geoMean);
			return geoMean;
			
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		/*
		calculate("21 121 -1");
		calculate("-1");
		*/
	}
}
