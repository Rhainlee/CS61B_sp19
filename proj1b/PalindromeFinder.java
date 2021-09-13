/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("C:/Users/rslee/Desktop/gitfile/CS61B_sp19/library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(5))) {
                System.out.println(word);
            }
        }
    }
}