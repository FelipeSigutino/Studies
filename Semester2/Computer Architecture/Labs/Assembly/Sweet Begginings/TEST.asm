.data
	Prompt: .asciiz "Enter Text: "
	input: .space 32
	
	hello: .asciiz "Hello\n"
	hello_msg: .asciiz "Hello :)\n"
	other_msg: .asciiz "Have a good day"

.text
main:
	li $v0, 4
	la $a0, Prompt
	syscall
	
	li $v0, 8
	la $a0, input
	li $a1, 32
	syscall
	
	la $t0, input
	la $t1, hello
	
compare_loop:
	
	lb $t2, 0($t0)
	lb $t3, 0($t1)
	
	bne $t2, $t3, not_hello
	
	beq $t2, $zero, is_hello
	
	addi $t0, $t0, 1
	addi $t1, $t1, 1
	
	j compare_loop
	
is_hello:
	li $v0, 4
	la $a0, hello_msg
	syscall
	j end
	
not_hello:
	li $v0, 4
	la $a0, other_msg
	syscall
	
end:
	li $v0, 10
	syscall