integer main()
{
	integer pid1,pid2;
	pid1 = Fork();
	if(pid1!=-2) then
		pid2 = Fork();
		
	endif;
	return 0;
}
