// A program to test open & delete

integer main()
{	
	integer a,b,c,d;
	a = Create("myfile.dat");
	a = Create("myfile1.dat");
	a = Open("myfile1.dat");
	print(a);
	b = Write(a,"chanddu");
	print(b);
	c = Open("myfile.dat");
	print(c);
	d = Write(c,"chandu");
	print(d);
	return 0;
}
