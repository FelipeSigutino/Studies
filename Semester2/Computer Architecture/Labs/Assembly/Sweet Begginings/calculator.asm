.data
	prompt_op: .asciiz "Choose operation (+ - * /): "
	prompt_a: .asciiz "Enter First Number: "
	prompt_b: .asciiz "Enter Second Number: "
	result_msg: .asciiz "Result: "
	newline: .asciiz "\n"

.text
main: 
	
	# Ask Operation
	li $v0, 4
	la $a0, prompt_op
	syscall
	
	li $v0, 12
	syscall
	move $t0, $v0
	
	# Newline Cleanup
	li $v0, 12
	syscall
	
	# First Number
	li $v0, 4
	la $a0, prompt_a
	syscall
	
	li $v0, 5
	syscall
	move $t1, $v0
	
	# Second Number
	li $v0, 4
	la $a0, prompt_b
	syscall
	
	li $v0, 5
	syscall
	move $t2, $v0
	
	# Deciding Operation
	beq $t0, 43, do_add # '+'
	beq $t0, 45, do_sub #'-'
	beq $t0, 42, do_mul #'*'
	beq $t0, 47, do_div #'/'
	
	j end
	
# Add
do_add:
	add $t3, $t1,$t2
	j print_result

# Substract
do_sub:
	sub $t3, $t1,$t2
	j print_result

# Multiply
do_mul:
	mul $t3, $t1,$t2
	j print_result

# Divide
do_div:
	div $t1,$t2
	mflo $t3
	j print_result

# We printinnn
print_result:
	li $v0, 4
	la $a0, result_msg
	syscall
	
	li $v0, 1
	move $a0, $t3
	syscall
	
	li $v0, 4
	la $a0, newline
	syscall
	
end:
	li $v0, 10
	syscall