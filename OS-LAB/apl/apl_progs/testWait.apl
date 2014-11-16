integer main(){
  integer a,b,c;
  a = Fork();
  if(a == -2) then
    a = Exec("odd.xsm");
  else
    a = Wait(1);
    print(a);
  endif;
  return 0;
}
