import java.util.*;
import java.math.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Neural {
	public static void main(String[] args) throws FileNotFoundException {
		int flag = Integer.valueOf(args[0]);
				
		if (flag == 100) {
			double ua, ub, va, vb, uc, vc;
			ua = inputSum(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]), 
					Double.valueOf(args[10]), Double.valueOf(args[11]));
			ub = inputSum(Double.valueOf(args[4]), Double.valueOf(args[5]), Double.valueOf(args[6]), 
					Double.valueOf(args[10]), Double.valueOf(args[11]));
			va = Math.max(ua, 0);
			vb = Math.max(ub, 0);
			uc = inputSum(Double.valueOf(args[7]), Double.valueOf(args[8]), Double.valueOf(args[9]), 
					va, vb);
			vc = 1/(1 + Math.exp(-1*uc));
			
			System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f", ua, va, ub, vb, uc, vc);
			
		} else if (flag == 200 || flag == 300 || flag == 400 || flag == 500) {
			double ua, ub, va, vb, uc, vc;
			ua = inputSum(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]), 
					Double.valueOf(args[10]), Double.valueOf(args[11]));
			ub = inputSum(Double.valueOf(args[4]), Double.valueOf(args[5]), Double.valueOf(args[6]), 
					Double.valueOf(args[10]), Double.valueOf(args[11]));
			va = Math.max(ua, 0);
			vb = Math.max(ub, 0);
			uc = inputSum(Double.valueOf(args[7]), Double.valueOf(args[8]), Double.valueOf(args[9]), 
					va, vb);
			vc = 1/(1 + Math.exp(-1*uc));
			
			double e, p1, p2;
			double y = Double.valueOf(args[12]);
			p1 = vc - y;
			e = .5 * Math.pow(p1, 2);
			p2 = p1 * vc * (1 - vc);
			
			double za1, za2, zb1, zb2;
			za1 = Double.valueOf(args[8]) * p2;
			zb1 = Double.valueOf(args[9]) * p2;
			za2 = za1 * bin(ua);
			zb2 = zb1 * bin(ub);
			
			double d0a, dx1a, dx2a, d0b, dx1b, dx2b, d0c, dac, dbc;
			d0a = 1 * za2;
			dx1a = Double.valueOf(args[10]) * za2;
			dx2a = Double.valueOf(args[11]) * za2;
			d0b = 1 * zb2;
			dx1b = Double.valueOf(args[10]) * zb2;
			dx2b = Double.valueOf(args[11]) * zb2;
			d0c = 1 * p2;
			dac = va * p2;
			dbc = vb * p2;
			
			
			if (flag == 200) { System.out.printf("%.5f %.5f %.5f", e, p1, p2); }
			
			if (flag == 300) { System.out.printf("%.5f %.5f %.5f %.5f", za1, za2, zb1, zb2); }
			
			if (flag == 400) { System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f", 
					d0a, dx1a, dx2a, d0b, dx1b, dx2b, d0c, dac, dbc); }
			
			if (flag == 500) {
				double n, uw1, uw2, uw3, uw4, uw5, uw6, uw7, uw8, uw9;
				n = Double.valueOf(args[13]);
				uw1 = Double.valueOf(args[1]) - (n * d0a);
				uw2 = Double.valueOf(args[2]) - (n * dx1a);
				uw3 = Double.valueOf(args[3]) - (n * dx2a);
				uw4 = Double.valueOf(args[4]) - (n * d0b);
				uw5 = Double.valueOf(args[5]) - (n * dx1b);
				uw6 = Double.valueOf(args[6]) - (n * dx2b);
				uw7 = Double.valueOf(args[7]) - (n * d0c);
				uw8 = Double.valueOf(args[8]) - (n * dac);
				uw9 = Double.valueOf(args[9]) - (n * dbc);
				
				ua = inputSum(uw1, uw2, uw3, Double.valueOf(args[10]), Double.valueOf(args[11]));
				ub = inputSum(uw4, uw5, uw6, Double.valueOf(args[10]), Double.valueOf(args[11]));
				va = Math.max(ua, 0);
				vb = Math.max(ub, 0);
				uc = inputSum(uw7, uw8, uw9, va, vb);
				vc = 1/(1 + Math.exp(-1*uc));
				p1 = vc - y;
				double ue = .5 * Math.pow(p1, 2);
				
				
				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", 
						Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]),
						Double.valueOf(args[4]), Double.valueOf(args[5]), Double.valueOf(args[6]), 
						Double.valueOf(args[7]), Double.valueOf(args[8]), Double.valueOf(args[9]));
				System.out.printf("%.5f\n", e);
				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", uw1, uw2, uw3, 
						uw4, uw5, uw6, uw7, uw8, uw9);
				System.out.printf("%.5f\n", ue);
			}
			
		} else if (flag == 600) {
			double w1, w2, w3, w4, w5, w6, w7, w8, w9, x1, x2, y, Ua, Va, Ub, Vb, Uc, Vc, n;
			w1 = Double.valueOf(args[1]);
			w2 = Double.valueOf(args[2]);
			w3 = Double.valueOf(args[3]);
			w4 = Double.valueOf(args[4]);
			w5 = Double.valueOf(args[5]);
			w6 = Double.valueOf(args[6]);
			w7 = Double.valueOf(args[7]);
			w8 = Double.valueOf(args[8]);
			w9 = Double.valueOf(args[9]);
			n = Double.valueOf(args[10]);
			
			File f1 = new File("hw2_midterm_A_train.txt");
			Scanner scn = new Scanner(f1);
			for (int j = 0; j < 67; j++) {
				x1 = scn.nextDouble();
				x2 = scn.nextDouble();
				y = scn.nextDouble();
				
				Ua = inputSum(w1, w2, w3, x1, x2);
				Ub = inputSum(w4, w5, w6, x1, x2);
				Va = Math.max(Ua, 0);
				Vb = Math.max(Ub, 0);
				Uc = inputSum(w7, w8, w9, Va, Vb);
				Vc = 1/(1 + Math.exp(-Uc));
				
				double e, p1, p2;
				e = .5 * Math.pow((Vc - y), 2);
				p1 = Vc - y;
				p2 = p1 * (Vc * (1 - Vc));
				
				double za1, za2, zb1, zb2;
				za1 = w8 * p2;
				zb1 = w9 * p2;
				za2 = za1 * bin(Ua);
				zb2 = zb1 * bin(Ub);
				
				double d0a, dx1a, dx2a, d0b, dx1b, dx2b, d0c, dac, dbc;
				d0a = za2;
				dx1a = x1 * za2;
				dx2a = x2 * za2;
				d0b = zb2;
				dx1b = x1 * zb2;
				dx2b = x2 * zb2;
				d0c = p2;
				dac = Va * p2;
				dbc = Vb * p2;
				
				w1 = w1 - (n * d0a);
				w2 = w2 - (n * dx1a);
				w3 = w3 - (n * dx2a);
				w4 = w4 - (n * d0b);
				w5 = w5 - (n * dx1b);
				w6 = w6 - (n * dx2b);
				w7 = w7 - (n * d0c);
				w8 = w8 - (n * dac);
				w9 = w9 - (n * dbc);
				
				File f2 = new File("hw2_midterm_A_eval.txt");
				Scanner scn2 = new Scanner(f2);
				double ex1, ex2, ey, eUa, eUb, eUc, eVa, eVb, eVc, sum;
				sum = 0;
				for (int i = 0; i < 25; i++) {
					ex1 = scn2.nextDouble();
					ex2 = scn2.nextDouble();
					ey = scn2.nextDouble();
					
					eUa = inputSum(w1, w2, w3, ex1, ex2);
					eUb = inputSum(w4, w5, w6, ex1, ex2);
					eVa = Math.max(eUa, 0);
					eVb = Math.max(eUb, 0);
					eUc = inputSum(w7, w8, w9, eVa, eVb);
					eVc = 1/(1 + Math.exp(-eUc));
					
					sum = sum + (.5 * Math.pow((eVc - ey), 2));
				}
				
				System.out.printf("%.5f %.5f %.5f\n", x1, x2, y);
				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", 
						w1, w2, w3, w4, w5, w6, w7, w8, w9);
				System.out.printf("%.5f\n", sum);
			}
			
		} else if (flag == 700) {
			
			double T = Double.valueOf(args[11]);
			double w1, w2, w3, w4, w5, w6, w7, w8, w9, x1, x2, y, Ua, Va, Ub, Vb, Uc, Vc, n;
			w1 = Double.valueOf(args[1]);
			w2 = Double.valueOf(args[2]);
			w3 = Double.valueOf(args[3]);
			w4 = Double.valueOf(args[4]);
			w5 = Double.valueOf(args[5]);
			w6 = Double.valueOf(args[6]);
			w7 = Double.valueOf(args[7]);
			w8 = Double.valueOf(args[8]);
			w9 = Double.valueOf(args[9]);
			n = Double.valueOf(args[10]);
			
			for (int k = 0; k < T; k++) {
				File f1 = new File("hw2_midterm_A_train.txt");
				Scanner scn = new Scanner(f1);
				for (int j = 0; j < 67; j++) {
					x1 = scn.nextDouble();
					x2 = scn.nextDouble();
					y = scn.nextDouble();
					
					Ua = inputSum(w1, w2, w3, x1, x2);
					Ub = inputSum(w4, w5, w6, x1, x2);
					Va = Math.max(Ua, 0);
					Vb = Math.max(Ub, 0);
					Uc = inputSum(w7, w8, w9, Va, Vb);
					Vc = 1/(1 + Math.exp(-Uc));
					
					double e, p1, p2;
					e = .5 * Math.pow((Vc - y), 2);
					p1 = Vc - y;
					p2 = p1 * (Vc * (1 - Vc));
					
					double za1, za2, zb1, zb2;
					za1 = w8 * p2;
					zb1 = w9 * p2;
					za2 = za1 * bin(Ua);
					zb2 = zb1 * bin(Ub);
					
					double d0a, dx1a, dx2a, d0b, dx1b, dx2b, d0c, dac, dbc;
					d0a = za2;
					dx1a = x1 * za2;
					dx2a = x2 * za2;
					d0b = zb2;
					dx1b = x1 * zb2;
					dx2b = x2 * zb2;
					d0c = p2;
					dac = Va * p2;
					dbc = Vb * p2;
					
					w1 = w1 - (n * d0a);
					w2 = w2 - (n * dx1a);
					w3 = w3 - (n * dx2a);
					w4 = w4 - (n * d0b);
					w5 = w5 - (n * dx1b);
					w6 = w6 - (n * dx2b);
					w7 = w7 - (n * d0c);
					w8 = w8 - (n * dac);
					w9 = w9 - (n * dbc);
					
				}
				
				File f2 = new File("hw2_midterm_A_eval.txt");
				Scanner scn2 = new Scanner(f2);
				double ex1, ex2, ey, eUa, eUb, eUc, eVa, eVb, eVc, sum;
				sum = 0;
				for (int i = 0; i < 25; i++) {
					ex1 = scn2.nextDouble();
					ex2 = scn2.nextDouble();
					ey = scn2.nextDouble();
					
					eUa = inputSum(w1, w2, w3, ex1, ex2);
					eUb = inputSum(w4, w5, w6, ex1, ex2);
					eVa = Math.max(eUa, 0);
					eVb = Math.max(eUb, 0);
					eUc = inputSum(w7, w8, w9, eVa, eVb);
					eVc = 1/(1 + Math.exp(-eUc));
					
					sum = sum + (.5 * Math.pow((eVc - ey), 2));
				}
				System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", 
						w1, w2, w3, w4, w5, w6, w7, w8, w9);
				System.out.printf("%.5f\n", sum);
			}

		} else if (flag == 800) {
			int count = 0;
			double prev_err = Double.MAX_VALUE;
			double curr_err = Double.MAX_VALUE;
			double T = Double.valueOf(args[11]);
			double w1, w2, w3, w4, w5, w6, w7, w8, w9, x1, x2, y, Ua, Va, Ub, Vb, Uc, Vc, n;
			
			w1 = Double.valueOf(args[1]);
			w2 = Double.valueOf(args[2]);
			w3 = Double.valueOf(args[3]);
			w4 = Double.valueOf(args[4]);
			w5 = Double.valueOf(args[5]);
			w6 = Double.valueOf(args[6]);
			w7 = Double.valueOf(args[7]);
			w8 = Double.valueOf(args[8]);
			w9 = Double.valueOf(args[9]);
			n = Double.valueOf(args[10]);
			
			while ((prev_err >= curr_err) && (count < T)) {
				prev_err = curr_err;
				File f1 = new File("hw2_midterm_A_train.txt");
				Scanner scn = new Scanner(f1);
				
				for (int j = 0; j < 67; j++) {
					x1 = scn.nextDouble();
					x2 = scn.nextDouble();
					y = scn.nextDouble();
					
					Ua = inputSum(w1, w2, w3, x1, x2);
					Ub = inputSum(w4, w5, w6, x1, x2);
					Va = Math.max(Ua, 0);
					Vb = Math.max(Ub, 0);
					Uc = inputSum(w7, w8, w9, Va, Vb);
					Vc = 1/(1 + Math.exp(-Uc));
					
					double e, p1, p2;
					e = .5 * Math.pow((Vc - y), 2);
					p1 = Vc - y;
					p2 = p1 * (Vc * (1 - Vc));
					
					double za1, za2, zb1, zb2;
					za1 = w8 * p2;
					zb1 = w9 * p2;
					za2 = za1 * bin(Ua);
					zb2 = zb1 * bin(Ub);
					
					double d0a, dx1a, dx2a, d0b, dx1b, dx2b, d0c, dac, dbc;
					d0a = za2;
					dx1a = x1 * za2;
					dx2a = x2 * za2;
					d0b = zb2;
					dx1b = x1 * zb2;
					dx2b = x2 * zb2;
					d0c = p2;
					dac = Va * p2;
					dbc = Vb * p2;
					
					w1 = w1 - (n * d0a);
					w2 = w2 - (n * dx1a);
					w3 = w3 - (n * dx2a);
					w4 = w4 - (n * d0b);
					w5 = w5 - (n * dx1b);
					w6 = w6 - (n * dx2b);
					w7 = w7 - (n * d0c);
					w8 = w8 - (n * dac);
					w9 = w9 - (n * dbc);
					
				}
				
				File f2 = new File("hw2_midterm_A_eval.txt");
				Scanner scn2 = new Scanner(f2);
				double ex1, ex2, ey, eUa, eUb, eUc, eVa, eVb, eVc, sum;
				sum = 0;
				for (int i = 0; i < 25; i++) {
					ex1 = scn2.nextDouble();
					ex2 = scn2.nextDouble();
					ey = scn2.nextDouble();
					
					eUa = inputSum(w1, w2, w3, ex1, ex2);
					eUb = inputSum(w4, w5, w6, ex1, ex2);
					eVa = Math.max(eUa, 0);
					eVb = Math.max(eUb, 0);
					eUc = inputSum(w7, w8, w9, eVa, eVb);
					eVc = 1/(1 + Math.exp(-eUc));
					
					sum = sum + (.5 * Math.pow((eVc - ey), 2));
				}
				
				curr_err = sum;
				count++;
			}
			
			System.out.println(count);
			System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", 
					w1, w2, w3, w4, w5, w6, w7, w8, w9);
			System.out.printf("%.5f\n", curr_err);
			
			File f3 = new File("hw2_midterm_A_test.txt");
			Scanner scn3 = new Scanner(f3);
			double ex1, ex2, ey, eUa, eUb, eUc, eVa, eVb, eVc;
			double corr = 0;
			
			for (int q = 0; q < 25; q++) {
				ex1 = scn3.nextDouble();
				ex2 = scn3.nextDouble();
				ey = scn3.nextDouble();
				
				eUa = inputSum(w1, w2, w3, ex1, ex2);
				eUb = inputSum(w4, w5, w6, ex1, ex2);
				eVa = Math.max(eUa, 0);
				eVb = Math.max(eUb, 0);
				eUc = inputSum(w7, w8, w9, eVa, eVb);
				eVc = 1/(1 + Math.exp(-eUc));
				
				if ((eVc >= 0.5 && ey == 1) || (eVc < 0.5 && ey == 0)) corr++;
			}
			
			System.out.printf("%.5f", corr/25);
		}
	}
	
	public static double inputSum(double w, double ww, double www, double in1, double in2) {
		return w + (ww*in1) + (www*in2);
	}
	
	public static double bin(double uj) {
		if (uj >= 0) return 1;
		else return 0;
	}
	
}
