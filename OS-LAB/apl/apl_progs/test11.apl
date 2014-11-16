integer main(){
  integer a,b;
  a = Fork();
  b = Fork();
  a = Getpid();
  b = Getppid();
  print(a);
  print(b);
  Exit();
  return 0;
}
