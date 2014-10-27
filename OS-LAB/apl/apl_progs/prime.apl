decl
	integer i,j,m,n;
enddecl
integer main() {
	read (n);
	i = 2;
while(i<=n) do
	j = 2;
	m = 0;
	while(j < i) do
		if(i % j == 0) then
			m = 1;
			break;
		endif;		
		j = j + 1; 
	endwhile;
	if(m == 0) then
		print (i);
	endif;
	i = i + 1;
endwhile;
return 0;
}
