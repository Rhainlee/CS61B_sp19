public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            char w = word.charAt(i);
            d.addLast(w);
        }
        return d;
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque<Character> d){
        if(d.size() <= 1){
            return true;
        }
        else if(d.removeLast() != d.removeFirst()){
            return false;
        }
        return isPalindromeHelper(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> d, CharacterComparator cc){
        if(d.size() <= 1){
            return true;
        }
        else if(!cc.equalChars(d.removeFirst(), d.removeLast())){
            return false;
        }
        return isPalindromeHelper(d, cc);
    }
}
