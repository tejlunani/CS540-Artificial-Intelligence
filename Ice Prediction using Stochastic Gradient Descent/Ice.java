import java.util.*;
import java.io.*;
import java.math.*;

public class Ice {
	public static void main(String[] args) {
		//Hard coding Data Set
		final int n = 162;
		int[] x = new int[162];
		for (int i = 0; i < 162; i++) {
			x[i] = 1855 + i;
		}
		int[] y = new int[162];
		y[0] = 118;
		y[1] = 151;
		y[2] = 121;
		y[3] = 96;
		y[4] = 110;
		y[5] = 117;
		y[6] = 132;
		y[7] = 104;
		y[8] = 125;
		y[9] = 118;
		y[10] = 125;
		y[11] = 123;
		y[12] = 110;
		y[13] = 127;
		y[14] = 131;
		y[15] = 99;
		y[16] = 126;
		y[17] = 144;
		y[18] = 136;
		y[19] = 126;
		y[20] = 91;
		y[21] = 130;
		y[22] = 62;
		y[23] = 112;
		y[24] = 99;
		y[25] = 161;
		y[26] = 78;
		y[27] = 124;
		y[28] = 119;
		y[29] = 124;
		y[30] = 128;
		y[31] = 131;
		y[32] = 113;
		y[33] = 88;
		y[34] = 75;
		y[35] = 111;
		y[36] = 97;
		y[37] = 112;
		y[38] = 101;
		y[39] = 101;
		y[40] = 91;
		y[41] = 110;
		y[42] = 100;
		y[43] = 130;
		y[44] = 111;
		y[45] = 107;
		y[46] = 105;
		y[47] = 89;
		y[48] = 126;
		y[49] = 108;
		y[50] = 97;
		y[51] = 94;
		y[52] = 83;
		y[53] = 106;
		y[54] = 98;
		y[55] = 101;
		y[56] = 108;
		y[57] = 99;
		y[58] = 88;
		y[59] = 115;
		y[60] = 102;
		y[61] = 116;
		y[62] = 115;
		y[63] = 82;
		y[64] = 110;
		y[65] = 81;
		y[66] = 96;
		y[67] = 125;
		y[68] = 104;
		y[69] = 105;
		y[70] = 124;
		y[71] = 103;
		y[72] = 106;
		y[73] = 96;
		y[74] = 107;
		y[75] = 98;
		y[76] = 65;
		y[77] = 115;
		y[78] = 91;
		y[79] = 94;
		y[80] = 101;
		y[81] = 121;
		y[82] = 105;
		y[83] = 97;
		y[84] = 105;
		y[85] = 96;
		y[86] = 82;
		y[87] = 116;
		y[88] = 114;
		y[89] = 92;
		y[90] = 98;
		y[91] = 101;
		y[92] = 104;
		y[93] = 96;
		y[94] = 109;
		y[95] = 122;
		y[96] = 114;
		y[97] = 81;
		y[98] = 85;
		y[99] = 92;
		y[100] = 114;
		y[101] = 111;
		y[102] = 95;
		y[103] = 126;
		y[104] = 105;
		y[105] = 108;
		y[106] = 117;
		y[107] = 112;
		y[108] = 113;
		y[109] = 120;
		y[110] = 65;
		y[111] = 98;
		y[112] = 91;
		y[113] = 108;
		y[114] = 113;
		y[115] = 110;
		y[116] = 105;
		y[117] = 97;
		y[118] = 105;
		y[119] = 107;
		y[120] = 88;
		y[121] = 115;
		y[122] = 123;
		y[123] = 118;
		y[124] = 99;
		y[125] = 93;
		y[126] = 96;
		y[127] = 54;
		y[128] = 111;
		y[129] = 85;
		y[130] = 107;
		y[131] = 89;
		y[132] = 87;
		y[133] = 97;
		y[134] = 93;
		y[135] = 88;
		y[136] = 99;
		y[137] = 108;
		y[138] = 94;
		y[139] = 74;
		y[140] = 119;
		y[141] = 102;
		y[142] = 47;
		y[143] = 82;
		y[144] = 53;
		y[145] = 115;
		y[146] = 21;
		y[147] = 89;
		y[148] = 80;
		y[149] = 101;
		y[150] = 95;
		y[151] = 66;
		y[152] = 106;
		y[153] = 97;
		y[154] = 87;
		y[155] = 109;
		y[156] = 57;
		y[157] = 87;
		y[158] = 117;
		y[159] = 91;
		y[160] = 62;
		y[161] = 65;
		
		//Actual Code		
		int flag = Integer.valueOf(args[0]);
		if (flag == 100) {
			//Print all points
			for (int i = 0; i < n; i++) {
				System.out.println(x[i] + " " + y[i]);
			}
		}
		else if (flag == 200) {
			//mean
			int sum = 0;
			for (int i = 0; i < n; i++) { sum = sum + y[i]; }
			double mean = (double) sum / (double) n;
			//standard deviation
			double sum2 = 0;
			for (int i = 0; i < n; i++) { 
				double j = (double) y[i] - mean;
				j = j*j;
				sum2 = sum2 + j;
			}
			sum2 = sum2 / 161;
			double sd = (double) Math.sqrt(sum2);
			
			System.out.println(n);
			System.out.printf("%.2f\n", mean);
			System.out.printf("%.2f\n", sd);
		}
		else if (flag == 300) {
			//Linear Regression
			double b0 = Double.valueOf(args[1]);
			double b1 = Double.valueOf(args[2]);
			
			double sum = 0;
			for (int i = 0; i < n; i++) {
				double j = b0 + b1* (double) x[i] - (double) y[i];
				j = j*j;
				sum = sum + j;
			}
			double mse = sum / (double) n;
			System.out.printf("%.2f\n", mse);
		}
		else if (flag == 400) {
			//Gradient Descent
			double b0 = Double.valueOf(args[1]);
			double b1 = Double.valueOf(args[2]);
			
			double sum = 0;
			for (int i = 0; i < n; i++) {
				double j = b0 + b1* (double) x[i] - (double) y[i];
				sum = sum + j;
			}
			double gd1 = sum*2 / (double) n;
			
			double sum2 = 0;
			for (int i = 0; i < n; i++) {
				double j = b0 + b1* (double) x[i] - (double) y[i];
				j = j * (double) x[i];
				sum2 = sum2 + j;
			}
			double gd2 = sum2*2 / (double) n;
			
			System.out.printf("%.2f\n", gd1);
			System.out.printf("%.2f\n", gd2);
			
		}
		else if (flag == 500) {
			double z = Double.valueOf(args[1]);
			int T = Integer.valueOf(args[2]);
			int t = 1;
			double b0 = 0;
			double b1 = 0;

			while (t <= T) {
				//b0-t
				double sum = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) x[i] - (double) y[i];
					sum = sum + j;
				}
				double gd1 = sum*2 / (double) n;
				//b1-t		
				double sum2 = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) x[i] - (double) y[i];
					j = j * (double) x[i];
					sum2 = sum2 + j;
				}
				double gd2 = sum2*2 / (double) n;
				
				b0 = b0 - z*gd1;
				b1 = b1 - z*gd2;
				//mse
				double sum3 = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) x[i] - (double) y[i];
					j = j*j;
					sum3 = sum3 + j;
				}
				double mse = sum3 / (double) n;
				
				System.out.print(t + " ");
				System.out.printf("%.2f ", b0);
				System.out.printf("%.2f ", b1);
				System.out.printf("%.2f\n", mse);
				t++;
			}
		}
		else if (flag == 600) {
			int sum = 0;
			for (int i = 0; i < n; i++) { sum = sum + x[i]; }
			double xmean = (double) sum / (double) n;
			int sum2 = 0;
			for (int i = 0; i < n; i++) { sum2 = sum2 + y[i]; }
			double ymean = (double) sum2 / (double) n;
			
			double sum3 = 0;
			for (int i = 0; i < n; i++) {
				double j = ((double) x[i] - xmean)*((double) y[i] - ymean);
				sum3 = sum3 + j;
			}
			double sum4 = 0;
			for (int i = 0; i < n; i++) {
				double j = (double) x[i] - xmean;
				j = j*j;
				sum4 = sum4 + j;
			}
			
			double b1 = sum3 / sum4;
			double b0 = ymean - b1*xmean;

			double sum5 = 0;
			for (int i = 0; i < n; i++) {
				double j = b0 + b1* (double) x[i] - (double) y[i];
				j = j*j;
				sum5 = sum5 + j;
			}
			double mse = sum5 / (double) n;
			
			System.out.printf("%.2f ", b0);
			System.out.printf("%.2f ", b1);
			System.out.printf("%.2f\n", mse);
		}
		else if (flag == 700) {
			double year = Double.valueOf(args[1]);
			int sum = 0;
			for (int i = 0; i < n; i++) { sum = sum + x[i]; }
			double xmean = (double) sum / (double) n;
			int sum2 = 0;
			for (int i = 0; i < n; i++) { sum2 = sum2 + y[i]; }
			double ymean = (double) sum2 / (double) n;
			
			double sum3 = 0;
			for (int i = 0; i < n; i++) {
				double j = ((double) x[i] - xmean)*((double) y[i] - ymean);
				sum3 = sum3 + j;
			}
			double sum4 = 0;
			for (int i = 0; i < n; i++) {
				double j = (double) x[i] - xmean;
				j = j*j;
				sum4 = sum4 + j;
			}
			
			double b1 = sum3 / sum4;
			double b0 = ymean - b1*xmean;
			double pred = b0 + b1*year;
			System.out.printf("%.2f", pred);
		}
		else if (flag == 800) {
			//normalizing x
			int sum11 = 0;
			for (int i = 0; i < n; i++) { sum11 = sum11 + x[i]; }
			double xmean = (double) sum11 / (double) n;
			double sum21 = 0;
			for (int i = 0; i < n; i++) { 
				double j = (double) x[i] - xmean;
				j = j*j;
				sum21 = sum21 + j;
			}
			sum21 = sum21 / 161;
			double sd = (double) Math.sqrt(sum21);
			
			double[] normx = new double[162];
			for (int i = 0; i < n; i++) {
				normx[i] = ((double) x[i] - xmean)/ sd;
			}
			
			//Repeat from FLAG==500
			double z = Double.valueOf(args[1]);
			int T = Integer.valueOf(args[2]);
			int t = 1;
			double b0 = 0;
			double b1 = 0;

			while (t <= T) {
				//b0-t
				double sum = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) normx[i] - (double) y[i];
					sum = sum + j;
				}
				double gd1 = sum*2 / (double) n;
				//b1-t		
				double sum2 = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) normx[i] - (double) y[i];
					j = j * (double) normx[i];
					sum2 = sum2 + j;
				}
				double gd2 = sum2*2 / (double) n;
				
				b0 = b0 - z*gd1;
				b1 = b1 - z*gd2;
				//mse
				double sum3 = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) normx[i] - (double) y[i];
					j = j*j;
					sum3 = sum3 + j;
				}
				double mse = sum3 / (double) n;
				
				System.out.print(t + " ");
				System.out.printf("%.2f ", b0);
				System.out.printf("%.2f ", b1);
				System.out.printf("%.2f\n", mse);
				t++;
			}
		}
		else if (flag == 900) {
			//normalizing x
			int sum11 = 0;
			for (int i = 0; i < n; i++) { sum11 = sum11 + x[i]; }
			double xmean = (double) sum11 / (double) n;
			double sum21 = 0;
			for (int i = 0; i < n; i++) { 
				double j = (double) x[i] - xmean;
				j = j*j;
				sum21 = sum21 + j;
			}
			sum21 = sum21 / 161;
			double sd = (double) Math.sqrt(sum21);
			
			double[] normx = new double[162];
			for (int i = 0; i < n; i++) {
				normx[i] = ((double) x[i] - xmean)/ sd;
			}
			
			//Repeat from FLAG==500
			double z = Double.valueOf(args[1]);
			int T = Integer.valueOf(args[2]);
			int t = 1;
			double b0 = 0;
			double b1 = 0;

			Random rng = new Random();
			while (t <= T) {
				//pick random
				int r = rng.nextInt(162);
				double gd1 = 2*(b0 + b1* (double) normx[r] - (double) y[r]);
				double gd2 = 2*(b0 + b1* (double) normx[r] - (double) y[r]) * (double) normx[r];
				
				b0 = b0 - z*gd1;
				b1 = b1 - z*gd2;
				//mse
				double sum3 = 0;
				for (int i = 0; i < n; i++) {
					double j = b0 + b1* (double) normx[i] - (double) y[i];
					j = j*j;
					sum3 = sum3 + j;
				}
				double mse = sum3 / (double) n;
				
				System.out.print(t + " ");
				System.out.printf("%.2f ", b0);
				System.out.printf("%.2f ", b1);
				System.out.printf("%.2f\n", mse);
				t++;
			}
		}
	}
}