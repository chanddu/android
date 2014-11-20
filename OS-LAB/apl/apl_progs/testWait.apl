integer main(){
  integer a,b,c;
  a = Fork();
  if(a == -2) then
    b = Exec("odd.xsm");
  else
    c = Wait(a);
    print(c);
  endif;
  return 0;
}
