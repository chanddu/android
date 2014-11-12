// A program to test open & delete & close

integer main()
{	
	integer a,b,c;
	a = Open("myfile1.dat");
	print(a);
	b = Close(a); 
	c = Delete("myfile1.dat");
	print(b);
	return 0;
}
