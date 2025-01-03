class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
   
    // Array1: all letters
    char[] sub = new char[52];
    sub[0] = 'a';
    sub[1] = 'b';
    sub[2] = 'c';
    sub[3] = 'd';
    sub[4] = 'e';
sub[5] = 'f';
    sub[6] = 'g';
    sub[7] = 'h';
    sub[8] = 'i';
    sub[9] = 'j';
sub[10] = 'k';
    sub[11] = 'l';
    sub[12] = 'm';
    sub[13] = 'n';
    sub[14] = 'o';
sub[15] = 'p';
    sub[16] = 'q';
    sub[17] = 'r';
    sub[18] = 's';
    sub[19] = 't';
sub[20] = 'u';
    sub[21] = 'v';
    sub[23] = 'w';
    sub[23] = 'x';
    sub[24] = 'y';
sub[25] = 'z';
sub[26] = 'A';
    sub[27] = 'B';
    sub[28] = 'C';
    sub[29] = 'D';
    sub[30] = 'E';
sub[31] = 'F';
    sub[32] = 'G';
    sub[33] = 'H';
    sub[34] = 'I';
sub[35] = 'J';
sub[36] = 'K';
    sub[37] = 'L';
    sub[38] = 'M';
    sub[39] = 'N';
    sub[40] = 'O';
sub[41] = 'P';
    sub[42] = 'Q';
    sub[43] = 'R';
    sub[44] = 'S';
    sub[45] = 'T';
sub[46] = 'U';
    sub[47] = 'V';
    sub[48] = 'W';
    sub[49] = 'X';
    sub[50] = 'Y';
sub[51] = 'Z';
    // Array2: Unicode characters
    char[] sub2 = new char[52];
    sub2[0] = '\u1266';
    sub2[1] = '\u3260';
    sub2[2] = '\u6265';
    sub2[3] = '\u2636';
    sub2[4] = '\u2836';  
    sub2[5] = '\u8347';
    sub2[6] = '\u9864';
    sub2[7] = '\u1765';
    sub2[8] = '\u5878';
    sub2[9] = '\u9765';
sub2[10] = '\u7193';
    sub2[11] = '\u1974';
    sub2[12] = '\u1546';
    sub2[13] = '\u3256';
    sub2[14] = '\u2586';
sub2[15] = '\u3245';
    sub2[16] = '\u5768';
    sub2[17] = '\u5456';
    sub2[18] = '\u1876';
    sub2[19] = '\u1111';
sub2[20] = '\u2222';
    sub2[21] = '\u9875';
    sub2[23] = '\u6547';
    sub2[23] = '\u6243';
    sub2[24] = '\u1235';
sub2[25] = '\u2356';
sub2[26] = '\u7246';
    sub2[27] = '\u9034';
    sub2[28] = '\u6240';
    sub2[29] = '\u1785';
    sub2[30] = '\u0926';  
    sub2[31] = '\u8347';
    sub2[32] = '\u5171';
    sub2[33] = '\u8927';
    sub2[34] = '\u3105';
    sub2[35] = '\u7660';
sub2[36] = '\u2300';
    sub2[37] = '\u7467';
    sub2[38] = '\u5432';
    sub2[39] = '\u0431';
    sub2[40] = '\u5391';
sub2[41] = '\u9434';
    sub2[42] = '\u9850';
    sub2[43] = '\u8748';
    sub2[44] = '\u6432';
    sub2[45] = '\u3567';
sub2[46] = '\u2246';
    sub2[47] = '\u1830';
    sub2[48] = '\u6224';
    sub2[49] = '\u3347';
    sub2[50] = '\u6573';
sub2[51] = '\u9698';
   
    // Encoding the plaintext:
    String file = Input.readFile("Original.txt");
    // Encode level 1 (substitution)
    String encodedMsg1 = subEncryption(file,sub,sub2);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (swap every 2 characters of a string)
    String encodedMsg2 = swap2(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // // Encode level 3 (cipher by 1,2,3,4,5)
    String encodedMsg3 = encode(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

   
    // Decoding the ciphertext:
    String file2 = Input.readFile("Encode3.txt");
    // Decode level 1  (undo the cipher)
    String decodedMsg1 = decode(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2 (unswap every 2 characters of a string)
    String decodedMsg2 = swap2(decodedMsg1);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (unsubstitution)
    String decodedMsg3 = subEncryption(decodedMsg2, sub2, sub);
    Input.writeFile("Decode3.txt", decodedMsg3);
   
   
  }
  // swap 2
  String swap2(String txt){
    String build = "";
    for(int x=0; x<=txt.length()-2; x+=2){
        build += txt.substring(x+1,x+2) + txt.substring(x,x+1);
    }

    if(txt.length()%2 == 1)
        build += txt.substring(txt.length()-1);
    return build;
  }

  // Cipher +1,2,3,4,5 encoding with no wrapping
  String encode(String txt) {
    String build = "";
    int ascii = 0;
    char ch = '\0';
    int[] shifts = {1, 2, 3, 4, 5};
   
    for (int x = 0; x < txt.length(); x++) {
        ch = txt.charAt(x);
        ascii = (int) ch;
        ascii += shifts[x % shifts.length];
       
        build += (char) ascii;
    }
    return build;
}
  // Cipher -1,2,3,4,5 encoding with no wrapping
  String decode(String txt) {
    String build = "";
    int ascii = 0;
    char ch = '\0';
    int[] shifts = {1, 2, 3, 4, 5};
   
    for (int x = 0; x < txt.length(); x++) {
        ch = txt.charAt(x);
        ascii = (int) ch;
        ascii -= shifts[x % shifts.length];
       
        build += (char) ascii;
    }
    return build;
}
  // Substitution encoding
  String subEncryption(String s, char[] sub, char[] sub2){
    String build = "";
    char ch ='\0';
    int index=0;
   
    for(int x=0; x<=s.length()-1; x++){
      ch = s.charAt(x);
      index = indexOf(ch,sub);
      if(index != -1){
        build += sub2[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }

  // identifying index of char within array
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x] == ch){
        return x;
      }
    }
    return -1;
  }

  // random integer generator
  int randInt(int lower, int upper){
    int range = upper - lower + 1;
    return (int)(Math.random()*range) + lower;
  }

}
