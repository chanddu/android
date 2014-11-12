integer main(){
  integer a,b,c;
  a = Fork();
  if(a==-2)then
    a = Exec("odd.xsm");
    c = Getpid();
    print(c);
  else
    a = Wait(1);
    print(a);
  endif;
  return 0;
}
