import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            count = Collections.frequency(corpus, w);
            
            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            //Creating intervals
            Interval[] x = new Interval[4700];
            for (int i = 0; i < 4700; i++) {
            	int count = Collections.frequency(corpus, i);
            	double prob = count/(double)corpus.size();
            	if (i == 0) {
            		x[i] = new Interval(0, prob);
            		continue;
            	}
            	x[i] = new Interval(x[i-1].right, (x[i-1].right) + prob);
            }
            //finding intersection point
            double r = (double)n1/(double)n2;
            for (int i = 0; i < 4700; i++) {
            	if (x[i].intersects(r, i)){
            		System.out.println(i);
            		System.out.println(String.format("%.7f", x[i].left));
            		System.out.println(String.format("%.7f", x[i].right));
            	}
            }
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            int j = 0;
            while (j < (corpus.size()-1)) {
            	if (corpus.get(j) == h){
            		if (corpus.get(j+1) == w) count++;
            		words_after_h.add(corpus.get(j+1));
            	}
            	j++;
            }
            
            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            double r = (double)n1/(double)n2;
            
            Interval[] x = new Interval[4700];
            for (int i = 0; i < 4700; i++) {
            	int count = 0;
                ArrayList<Integer> words_after_h = new ArrayList<Integer>();
                int j = 0;
                while (j < (corpus.size()-1)) {
                	if (corpus.get(j) == h){
                		if (corpus.get(j+1) == i) count++;
                		words_after_h.add(corpus.get(j+1));
                	}
                	j++;
                }
                double prob = count/(double)words_after_h.size();
            	if (i == 0) {
            		x[i] = new Interval(0, prob);
            		continue;
            	}
            	x[i] = new Interval(x[i-1].right, (x[i-1].right) + prob);
            }
            
            for (int i = 0; i < 4700; i++) {
            	if (x[i].intersects(r, i)){
            		System.out.println(i);
            		System.out.println(String.format("%.7f", x[i].left));
            		System.out.println(String.format("%.7f", x[i].right));
            	}
            }
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            int j = 0;
            while (j < (corpus.size()-2)) {
            	if (corpus.get(j) == h1 && corpus.get(j+1) == h2){
            		if (corpus.get(j+2) == w) count++;
            		words_after_h1h2.add(corpus.get(j+2));
            	}
            	j++;
            }
            
            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            double r = (double)n1/(double)n2;
            ArrayList<Interval> x = new ArrayList<Interval>();
            
            for (int i = 0; i < 4700; i++) {
                int count = 0;
                ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
                int j = 0;
                while (j < (corpus.size()-2)) {
                	if (corpus.get(j) == h1 && corpus.get(j+1) == h2){
                		if (corpus.get(j+2) == i) count++;
                		words_after_h1h2.add(corpus.get(j+2));
                	}
                	j++;
                }
                
                double prob = count/(double)words_after_h1h2.size();
                if (prob != 0) {
                	if (x.size() == 0) {
                		x.add(new Interval(0, prob, i));
                		continue;
                	}
                	x.add(new Interval(x.get(x.size()-1).right, (x.get(x.size()-1).right) + prob, i));
                }
            }
            
            int ESKETIT = 0;
            for (int i = 0; i < x.size(); i++) {
            	if (x.get(i).intersects(r, i)){
            		System.out.println(x.get(i).wordind);
            		System.out.println(String.format("%.7f", x.get(i).left));
            		System.out.println(String.format("%.7f", x.get(i).right));
            		ESKETIT = 1;
            	}
            }
            if (ESKETIT == 0) System.out.println("undefined");
            
        }
        else if(flag == 700) {
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                
                //UNIGRAM
                Interval[] x = new Interval[4700];
                for (int i = 0; i < 4700; i++) {
                	int count = Collections.frequency(corpus, i);
                	double prob = count/(double)corpus.size();
                	if (i == 0) {
                		x[i] = new Interval(0, prob);
                		continue;
                	}
                	x[i] = new Interval(x[i-1].right, (x[i-1].right) + prob);
                }
                for (int i = 0; i < 4700; i++) {
                	if (x[i].intersects(r, i)){
                		h1 = i;
                	}
                }
                
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                //BIGRAM
                Interval[] y = new Interval[4700];
                for (int i = 0; i < 4700; i++) {
                	int count = 0;
                    ArrayList<Integer> words_after_h = new ArrayList<Integer>();
                    int j = 0;
                    while (j < (corpus.size()-1)) {
                    	if (corpus.get(j) == h1){
                    		if (corpus.get(j+1) == i) count++;
                    		words_after_h.add(corpus.get(j+1));
                    	}
                    	j++;
                    }
                    double prob = count/(double)words_after_h.size();
                	if (i == 0) {
                		y[i] = new Interval(0, prob);
                		continue;
                	}
                	y[i] = new Interval(y[i-1].right, (y[i-1].right) + prob);
                }
                
                for (int i = 0; i < 4700; i++) {
                	if (y[i].intersects(r, i)){
                		h2 = i;
                	}
                }
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                //BIGRAM
                Interval[] y = new Interval[4700];
                for (int i = 0; i < 4700; i++) {
                	int count = 0;
                    ArrayList<Integer> words_after_h = new ArrayList<Integer>();
                    int j = 0;
                    while (j < (corpus.size()-1)) {
                    	if (corpus.get(j) == h1){
                    		if (corpus.get(j+1) == i) count++;
                    		words_after_h.add(corpus.get(j+1));
                    	}
                    	j++;
                    }
                    double prob = count/(double)words_after_h.size();
                	if (i == 0) {
                		y[i] = new Interval(0, prob);
                		continue;
                	}
                	y[i] = new Interval(y[i-1].right, (y[i-1].right) + prob);
                }
                
                for (int i = 0; i < 4700; i++) {
                	if (y[i].intersects(r, i)){
                		h2 = i;
                	}
                }
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                //TRIGRAM
                ArrayList<Interval> x = new ArrayList<Interval>();
                
                for (int i = 0; i < 4700; i++) {
                    int count = 0;
                    ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
                    int j = 0;
                    while (j < (corpus.size()-2)) {
                    	if (corpus.get(j) == h1 && corpus.get(j+1) == h2){
                    		if (corpus.get(j+2) == i) count++;
                    		words_after_h1h2.add(corpus.get(j+2));
                    	}
                    	j++;
                    }
                    
                    double prob = count/(double)words_after_h1h2.size();
                    if (prob != 0) {
                    	if (x.size() == 0) {
                    		x.add(new Interval(0, prob, i));
                    		continue;
                    	}
                    	x.add(new Interval(x.get(x.size()-1).right, (x.get(x.size()-1).right) + prob, i));
                    }
                }
                
                int ESKETIT = 0;
                for (int i = 0; i < x.size(); i++) {
                	if (x.get(i).intersects(r, i)){
                		w = x.get(i).wordind;
                		ESKETIT = 1;
                	}
                }
                if (ESKETIT == 0) {
                	//BIGRAM again in case the pair is not in the corpus
                	Interval[] y = new Interval[4700];
                    for (int i = 0; i < 4700; i++) {
                    	int count = 0;
                        ArrayList<Integer> words_after_h = new ArrayList<Integer>();
                        int j = 0;
                        while (j < (corpus.size()-1)) {
                        	if (corpus.get(j) == h2){
                        		if (corpus.get(j+1) == i) count++;
                        		words_after_h.add(corpus.get(j+1));
                        	}
                        	j++;
                        }
                        double prob = count/(double)words_after_h.size();
                    	if (i == 0) {
                    		y[i] = new Interval(0, prob);
                    		continue;
                    	}
                    	y[i] = new Interval(y[i-1].right, (y[i-1].right) + prob);
                    }
                    
                    for (int i = 0; i < 4700; i++) {
                    	if (y[i].intersects(r, i)){
                    		w = i;
                    	}
                    }
                }
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}

class Interval {
	public double left;
	public double right;
	public int wordind;
	
	public Interval(double l, double r) {
		left = l;
		right = r;
	}
	
	public Interval(double l, double r, int wind) {
		left = l;
		right = r;
		wordind = wind;
	}
	
	public boolean intersects(double x, int wordind) {
		if (wordind == 0) {
			if (x >= left && x <= right) return true;
			else return false;
		}
		if (x > left && x <= right) return true;
		else return false;
	}
}