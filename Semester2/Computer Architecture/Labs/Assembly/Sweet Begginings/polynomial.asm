.data
	Welcome: .asciiz "Welcome to Polynomial Calculator!\n"
	Informer: .asciiz "Please Input only Integers:\n"
	prompt_a: .asciiz "Enter a: "
	prompt_b: .asciiz "Enter  b: "
	prompt_c: .asciiz "Enter c: "
	prompt_d: .asciiz "Enter d: "
	prompt_x: .asciiz "Enter x: "
	result_msg: .asciiz "Result: "
	newline: .asciiz "\n"
.text

.main:
	#Welcome To String 
	li $v0, 4
	la $a0, Welcome
	syscall
	
	#Informer To String
	li $v0, 4
	la $a0, Informer
	syscall

	#A
	li $v0, 4
	la $a0, prompt_a
	syscall
	
	li $v0, 5
	# We could also write here:
	#li $v0, 6
	#In order to read a float current program Works for ints
	syscall
	move $t0, $v0
	
	#B
	li $v0, 4
	la $a0, prompt_b
	syscall
	
	li $v0, 5
	syscall
	move $t1, $v0
	
	#C
	li $v0, 4
	la $a0, prompt_c
	syscall
	
	li $v0, 5
	syscall
	move $t2, $v0
	
	#D
	li $v0, 4
	la $a0, prompt_d
	syscall
	
	li $v0, 5
	syscall
	move $t3, $v0
	
	#X
	li $v0, 4
	la $a0, prompt_x
	syscall
	
	li $v0, 5
	syscall
	move $t4, $v0
	
	#calculations
	mul $t5, $t0, $t4
	# Here in order to calculate floats we would just add .s after calculation type i.e mul.s
	add $t5, $t5, $t1
	 
	mul $t5, $t5, $t4
	add $t5, $t5, $t2	
	
	mul $t5, $t5, $t4
	add $t5, $t5, $t3
	
	#Finish
	li $v0, 4
	la $a0,result_msg
	syscall
	
	li $v0, 1
	move $a0, $t5
	syscall
	
	li $v0, 10
	syscall
	
	
