package numquiz;

import java.util.ArrayList;
import java.util.List;

public class NumberQuestion {
  private String answer;
  private String text;
  private static final int[] bases = { 2, 8, 10, 16 };
  private static final char[] literals = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  
  public NumberQuestion() {
    int type = (int)Math.floor(Math.random() * 2);
    if(type == 0) {
      int fromBase = bases[(int)Math.floor(bases.length * Math.random())];
      int toBase = bases[(int)Math.floor(bases.length * Math.random())];
      while(fromBase == toBase) {
        toBase = bases[(int)Math.floor(bases.length * Math.random())];
      }
      int value = (int)Math.floor(Math.random() * 120);
      String fromEncoded = encode(value, fromBase);
      String toEncoded = encode(value, toBase);
      this.text = "Find the encoding base " + toBase + " of " + fromEncoded + " base " + fromBase;
      this.answer = toEncoded;
    } else if(type == 1) {
      int base = bases[(int)Math.floor(bases.length * Math.random())];
      while(base != 2 && base != 16) {
        base = bases[(int)Math.floor(bases.length * Math.random())];
      }
      int value1 = (int)Math.floor(Math.random() * 200 * base);
      int value2 = (int)Math.floor(Math.random() * 200 * base);
      int sum = value1 + value2;
      String value1Encoded = encode(value1, base);
      String value2Encoded = encode(value2, base);
      String sumEncoded = encode(sum, base);
      String longValue = value1Encoded.length() <= value2Encoded.length() ? value2Encoded : value1Encoded;
      String shortValue = value1Encoded.length() <= value2Encoded.length() ? value1Encoded : value2Encoded;
      int padding = longValue.length() - shortValue.length();
      for(int i = 0; i < padding; i++) {
        shortValue = " " + shortValue;
      }
      longValue = "  " + longValue;
      shortValue = "+ " + shortValue;
      String additionTable = longValue + "\n" + shortValue;
      this.text = "Find in base " + base + ": \n" + additionTable;
      this.answer = sumEncoded;
    }
  }
  
  private static String encode(int value, int base) {
    int lengthNeeded = 0;
    while(Math.pow(base, lengthNeeded) - 1 < value) {
      lengthNeeded++;
    }
    char[] encodedChars = new char[lengthNeeded];
    for(int i = 0; i < encodedChars.length; i++) {
      int literalIndex = 0;
      int placeValue = (int)Math.pow(base, encodedChars.length - 1 - i);
      for(int j = 0; j < base; j++) {
        if(value - (placeValue * j) < 0) break;
        literalIndex = j;
      }
      value -= literalIndex * placeValue;
      encodedChars[i] = literals[literalIndex];
    }
    return new String(encodedChars);
  }
  
  private static int decode(String string, int base) {
    List<Character> literalList = new ArrayList<Character>(16);
    for(char c : literals) {
      literalList.add(c);
    }
    int value = 0;
    char[] chars = string.toCharArray();
    for(int i = 0; i < chars.length; i++) {
      int placeValue = (int)Math.pow(base, chars.length - 1 - i);
      value += placeValue * literalList.indexOf(chars[i]);
    }
    return value;
  }

  public String getAnswer() {
    return answer;
  }

  public String getText() {
    return text;
  }
  
}
