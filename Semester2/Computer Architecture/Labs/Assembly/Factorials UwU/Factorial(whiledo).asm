.data
	Welcome: .asciiz "Welcome To Factorial Calculator\n"
	Input_n: .asciiz"Please input integer that you would want to get factorial of:"
	Overflow_msg: .asciiz"The Overflow Occured >_<"
	Result_msg: .asciiz "N! = "
	newline: .asciiz "\n"
.text
.main:
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
	move $t0, $v0
	
	# blez $t0, invalid # to check if n is natural >=0
	
	#declare factorial = 1 variable:
	li $t1, 1
	
	#declare i = 1 variable: 
	li $t2, 1
	
while_loop:
	#branch if greater than
	bgt $t2, $t0, end_loop
	
	mult $t1, $t2
	
	#checking overflow here
	mfhi $t3 #move from high register
	bnez $t3, overflow #branch if not equal zero
	
	mflo $t1 #move from LO register
	
	addi $t2, $t2, 1
	
	j while_loop #jump
	
end_loop:
	
	li $v0, 4
	la $a0, Result_msg
	syscall
	
	# We printing the rezolt ova hija
	li $v0, 1
	la $a0, ($t1)
	syscall
	
	# nowa linia
	li $v0, 4
	la $a0, newline
	syscall
	
	j exit # skaczemy na wyjście
	
overflow:
	li $v0, 4
	la $a0, Overflow_msg
	syscall
	
exit:
	li $v0, 10
	syscall
		
	