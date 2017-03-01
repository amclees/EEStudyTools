package numquiz;

import java.util.Scanner;

public class NumberQuiz {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("How many questions would you like?");
    int questions = sc.nextInt();
    sc.nextLine();
    int done = 0;
    int right = 0;
    while(done < questions) {
      NumberQuestion q = new NumberQuestion();
      System.out.println(q.getText());
      String input = sc.nextLine().trim().toUpperCase();
      System.out.println(q.getAnswer() + " is the correct answer.");
      if(input.equals(q.getAnswer())) right++;
      done++;
      System.out.printf("You have answered %d/%d questions correctly so far.%n", right, done);
    }
    int percent = (int)Math.round(100.0d * ((double)right) / ((double)done));
    System.out.printf("You answered %d/%d questions correctly for a total of %d%%", right, done, percent);
  }
}
