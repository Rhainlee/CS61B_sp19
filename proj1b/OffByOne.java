public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        int d = Math.abs(x - y);
        return d == 1;
    }
}
