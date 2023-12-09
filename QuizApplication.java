import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//1
class Question{
    private String questionText;
    private String[] options;
    private String correctAnswer;
    private String questionType;

    public Question(String questionText, String[] options, String correctAnswer, String questionType){
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    //Getter Methods
    public String getQuestionText(){
        return questionText;
    }
    public String[] getOptions(){
        return options;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }
    public String getQuestionType(){
        return questionType;
    }
    public boolean isCorrect(String userAnswer){ return userAnswer.equalsIgnoreCase(correctAnswer);}
}
//2
class QuestionRepository{
    private List<Question> questions = new ArrayList<>();


    public void initializeQuestions(){
        String[] options1 = {"A. Java", "B. Python", "C. C++", "D. JavaScript"};
        Question question1 = new Question("1. Which programming language is known for its 'Write Once, Run Anywhere' principle?", options1, "A. Java", "Multiple Choice");

        String[] options2 = {"True", "False"};
        Question question2 = new Question("2. Java is a purely object-oriented programming language. (True/False)", options2, "False", "True/False");

        String [] options3 = {"A. Berlin","B. Madrid","C. Paris","D. Rome"};
        Question question3 = new Question("3. What is the capital of France", options3,"C. Paris", "Multiple Choice");

        String[] options4 = {"True", "False"};
        Question question4 = new Question("4. The Earth is flat.. (True/False)", options4, "False", "True/False");

        String[] options5 = {"A. Mars","B. Venus","C. Jupiter","D. Saturn"};
        Question question5 = new Question("5. Which planet is known as the Red Planet?",options5,"A. Mars","Multiple Choice");

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
    }

    public List<Question> getQuestions(){
        return questions;
    }
}
public class QuizApplication {
    public static void main(String[] args){
        QuestionRepository questionRepository = new QuestionRepository();
        questionRepository.initializeQuestions();

        List<Question> questions = questionRepository.getQuestions();

        Scanner scanner = new Scanner(System.in);
        int userScore = 0;

        for (Question question: questions) {
            System.out.println(question.getQuestionText());

            if(question.getQuestionType().equalsIgnoreCase("Multiple Choice")){
                for(String option: question.getOptions()){
                    System.out.println(option);
                }
            }

            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if(userAnswer.equalsIgnoreCase(question.getCorrectAnswer())){
                System.out.println("Correct!");
                userScore++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getCorrectAnswer());
            }
            System.out.println();
        }
        System.out.println("Quiz Completed! Your final score is: " + userScore + " out of " + questions.size());
        scanner.close();

    }
}
