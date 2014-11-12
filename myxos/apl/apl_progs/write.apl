// APL program to test WRITE SYSTEM CALL

integer main()
{	
	integer a,b,c;
	a = Open("myfile1.dat");
	b = Write(a,a);
	c = Close(a);
	print(b);
	return 0;
}
