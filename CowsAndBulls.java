import java.util.*;

public class CowsAndBulls
{
    public static int[] answer = new int[4];
    public static int[] guess = new int[4];

    public static void main (String[] args)
    {
        Scanner user = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying)
        {
            initBoard();
            printBlanks(answer);
            for (int i = 15; i >= 0; i--)
            {
                if (i == 0)
                {
                    System.out.println("You have run out of guesses and lost.");
                    printValues(answer);
                    break;
                } // if

                System.out.print("Please guess the correct order of numbers (" + i + " guesses remain):\t");
                for (int j = 0; j < guess.length; j++)
                {
                    guess[j] = user.nextInt();
                } // for
                user.nextLine();

                if (!checkBulls())
                {
                    checkCows();
                } else {
                   System.out.println("Congratulations! You have won the game!");
                   break;
                } // if
            } // for

            System.out.print("Would you like to play again? (y/n)\t");
            String exit = user.next();
            user.nextLine();

            if (exit.equalsIgnoreCase("n"))
            {
                keepPlaying = false;
            } // if
        } // while

        user.close();
    } // main

    public static void initBoard()
    {
        Random random = new Random(System.currentTimeMillis());
        int currValue = random.nextInt(10);
        for (int i = 0; i < answer.length; i++)
        {
            answer[i] = currValue;
        } // for

        for (int i = 1; i < answer.length; i++)
        {
            boolean repeat = true;
            
            while (repeat)
            {
                repeat = false;
                currValue = random.nextInt(10);
                for (int j = 0; j < i; j++)
                {
                    if (answer[j] == currValue)
                    {
                        repeat = true;
                    } // if
                } // for
            } // while

            answer[i] = currValue;
        } // for
    } // initBoard

    public static void printBlanks(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            System.out.print("_ ");
        } // for
        System.out.println("_");
    } // printBlanks

    public static void printValues(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            System.out.print(array[i] + " ");
        } // for
        System.out.println(array[array.length - 1]);
    } // printBlanks

    public static boolean checkBulls()
    {
        boolean victory = true;
        int bulls = 0;

        for (int i = 0; i < answer.length; i++)
        {
            if (answer[i] == guess[i])
            {
                bulls++;
            } else {
                victory = false;
            } // if
        } // for
        
        if (!victory)
        {
            System.out.println("There are " + bulls + " bull(s) on the board.");
        } // if

        return victory;
    } // checkBulls

    public static void checkCows()
    {
        int cows = 0;

        for (int i = 0; i < answer.length; i++)
        {
            for (int j = 0; j < guess.length; j++)
            {
                if (j == i)
                {
                    continue;
                } // if

                if (guess[j] == answer[i])
                {
                    cows++;
                } // if
            } // for
        } // for

        System.out.println("There are " + cows + " cow(s) on the board.");
    } // checkCows
} // CowsAndBulls