class Solution {
  public int minOperations(String[] logs) {
    int operation = 0;
    for (String log : logs) {
      if (log.equals("../")) { // move to the parent folder
        if (operation > 0) {
          operation--;
        }
      } else if (!log.equals("./")) { // move to the child folder
        operation++;
      }
    }

    return operation;
  }
}