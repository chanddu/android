integer main()
{
	integer pid1,pid2,pid3,pid;
	pid1 = Fork();
	if(pid1!=-2) then
		pid2 = Fork();
		pid = Getpid();
		print(pid);
	endif;
	pid3 = Fork();
	pid = Getppid();
	print(pid);
	return 0;
}
