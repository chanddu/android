integer main()
{
	integer pid,b;
	string a;
	print("-1 to exit");
	print("enter fname");
	read(a);
	while(a != "-1") do
		pid = Fork();
		if(pid == -1) then
			print("error");
		endif;
		breakpoint;
		if(pid != -2) then
			b = Wait(pid);
		endif;
		breakpoint;
		if(pid == -2) then
			b = Exec(a);
			if(b == -1) then
				print("fname error");
				break;
			endif;
		endif;
		print("enter fname");
		read(a);
	endwhile;
	return 0;
}
