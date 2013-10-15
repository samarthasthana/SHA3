package sam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * 
 * @author Sam
 */
public class Main {

	public String padMyBin(String input, int bin_size) {
		if (input.length() == (bin_size * 1088)) {
			return input;
		}
		if (input.length() < (bin_size * 1088)) {
			input = input + "1"; // the first bit of padding is done here
		}
		while (input.length() < ((bin_size * 1088) - 1)) {
			input = input + "0";
		}
		input = input + "1";
		return input;
	}

	public String convertToBin(int val) {
		String interim = Integer.toBinaryString(val);
		if (interim.length() < 8) {
			while (interim.length() != 8) {
				interim = "0" + interim;
			}
		}
		return interim;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		Main obj = new Main();
		Sponge spObj = new Sponge();
		char[] result;
		String hash = "", temp = "";
		if(args.length==0){
			System.out.println("Please provide an file name as command line argument");
			System.exit(0);
		}
		if (args[0]!="") {
			InputStream inf = new FileInputStream(args[0]);
			int size = inf.available();
			int bin_size = 0;
			// check if the size of the message is less than block size 1088
			if ((size * 8) < 1088) {
				bin_size = 1;
			} else {
				if ((size * 8) == 1088) {
					bin_size = 2;
				} else {
					if ((size * 8) > 1088) {
						bin_size = ((size * 8) / 1088) + 1;
					}
				}
			}
			String msg_binary = "";

			for (int i = 0; i < size; i++) {
				msg_binary = msg_binary + obj.convertToBin((int) inf.read());
			}
			// convert the int value and store in the block
			result = spObj.work_sponge(obj.padMyBin(msg_binary, bin_size),
					bin_size);
			int i_temp;
			for (int k = 0; k < 256; k++) {
				if (k % 4 == 0 && k != 0) {
					i_temp = Integer.parseInt(temp, 2);
					// System.out.printf("%d \n",i_temp);
					hash = hash + Integer.toHexString(i_temp);
					temp = "";
				}
				temp = temp + result[k];
			}
			System.out.println(hash);
			File out = new File("result.txt");
			PrintWriter outf = new PrintWriter(out);
			outf.println(hash);
			outf.close();
			inf.close();

		}
	}
}
