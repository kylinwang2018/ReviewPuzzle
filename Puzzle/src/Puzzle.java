import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Puzzle {

	private static ArrayList<String> questions = new ArrayList<>();
	private static ArrayList<String> answers = new ArrayList<>();

	public static void main(String[] args){
		readFiles(args[0]);

		Random r=new Random();
		Scanner scan = new Scanner(System.in);
		while(true){
			int qusNum = r.nextInt(questions.size()-1);
			int ansPos = r.nextInt(3);
			int[] fakeAns = new int[3];
			fakeAns[0] = r.nextInt(questions.size()-1);
			fakeAns[1] = r.nextInt(questions.size()-1);
			fakeAns[2] = r.nextInt(questions.size()-1);
			String[] puzzleAns = new String[4];
			System.out.println(questions.get(qusNum));
			int fakeNum = 0;
			for (int i = 0; i < 4 ; i++){
				if (i == ansPos){
					puzzleAns[i] = answers.get(qusNum);
					System.out.println(i+".\t"+ puzzleAns[i]);
				}else {
					puzzleAns[i] =answers.get(fakeAns[fakeNum]);
					System.out.println(i+".\t"+puzzleAns[i]);
					fakeNum++;
				}
			}


			if (scan.hasNextLine()) {
				String str2 = scan.nextLine();
				if (puzzleAns[Integer.parseInt(str2)].toLowerCase().equals(puzzleAns[ansPos].toLowerCase())){
					System.out.println("Correct");
				}else{
					System.out.println("Wrong!");
				}
			}

		}

	}


	private static void readFiles(String dirPath){
		// check the availability of the folder
		try {
			File folder = new File(dirPath);
			if (!folder.exists() && !folder.isDirectory()) {
				System.out.println("Folder cannot be found!");
				System.exit(-1);
			}

			// check the files
			File questionFile = new File(".\\" + dirPath + "\\question.txt");
			File answerFile = new File(".\\" + dirPath + "\\answer.txt");
			if (!questionFile.exists() || !answerFile.exists()) {
				System.out.println("Question or Answer file cannot be found");
				System.exit(-1);
			} else {
				// read files
				InputStreamReader qusReader = new InputStreamReader(new FileInputStream(questionFile));
				InputStreamReader ansReader = new InputStreamReader(new FileInputStream(answerFile));
				BufferedReader qusBr = new BufferedReader(qusReader);
				BufferedReader awsBr = new BufferedReader(ansReader);
				String line;
				while ((line = qusBr.readLine()) != null) {
					questions.add(line);
				}
				while ((line = awsBr.readLine()) != null) {
					answers.add(line);
				}
				// check size of lists
				if (questions.size() != answers.size()){
					System.out.println("Different size");
					System.exit(-1);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
