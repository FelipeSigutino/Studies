.data
	Welcome: .asciiz "Welcome to BIG factorial calculator :)\n"
	Input_n: .asciiz "Please Input Integer Value that you would like to calculate factorial of: "
	Result: .asciiz " !N = "
	newline: .asciiz "\n"
	Digits: .space 200
	
.text
main:
	#Welcome to String
	li $v0, 4
	la $a0, Welcome
	syscall
	
	#Input Integer
	li $v0, 4
	la $a0, Input_n
	syscall
	
	li $v0, 5
	syscall
	move $s0, $v0
	
	# blez $s0, invalid # to check if n is natural >=0
	
	la $s1, Digits
	
	li $t0, 1
	sb $t0, 0($s1)
	
	li $s2,1
	li $s3,2
	
outer_while:
	bgt $s3,$s0, print_result
	
	li $t1,0
	li $t2,0
	
	inner_while:
		bge $t2,$s2, carry
		
		add $t3, $s1 , $t2
		lb $t4,0($t3)
		
		mul $t5, $t4, $s3
		
		add $t5, $t5, $t1
		
		li $t6, 10
		div $t5, $t6
		
		mfhi $t7
		mflo $t1
		
		sb $t7, 0($t3)
		
		addi $t2,$t2,1
		j inner_while
		
	carry:
		blez $t1, next_i #branch if lower or equal to zero
		li $t6, 10
		div $t1, $t6
	
		mfhi $t7
		mflo $t1 
		
		add $t3, $s1, $s2
		sb $t7, 0($t3) #store byte
		
		addi $s2,$s2,1
		
		j carry
		
	next_i:
	
	addi $s3,$s3,1
	j outer_while
	
print_result:
	li $v0, 4
	la $a0, Result
	syscall
	
	addi $t2, $s2,-1
	
print_loop:
	bltz $t2,exit
	
	add $t3,$s1,$t2
	lb $a0, 0($t3)
	
	li $v0, 1
	syscall
	
	addi $t2, $t2, -1
	j print_loop

exit:

    	li $v0, 4
   	la $a0, newline
  	syscall

    li $v0, 10
    syscall
	
	
	