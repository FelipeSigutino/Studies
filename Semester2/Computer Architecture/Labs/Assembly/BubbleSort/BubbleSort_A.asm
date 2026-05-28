.data 
	Welcome: .asciiz "Welcome To BubbleSort Algorithm!\n"
	Input_Num: .asciiz "Input Number:"
	Input_N: .asciiz "Input N (<100):"
	comma: .asciiz ", "
	newline: .asciiz "\n"
	
	array: .word 0:100	
.text

main: 
	li $v0, 4
	la $a0, Welcome
	syscall
	
	li $v0, 4
	la $a0, Input_N
	syscall
	
	li $v0, 5
	syscall
	move $s0, $v0
	
	li $t0, 0
	
read_loop:
	beq $t0, $s0, read_done
	
	li $v0, 4
	la $a0, Input_Num
	syscall
	
	li $v0, 5
	syscall
	
	la $t1, array
	mul $t2, $t0, 4
	add $t1, $t1, $t2
	sw $v0, 0($t1)
		
	
	addi $t0, $t0, 1
	j read_loop
	
read_done:
	
	la $a0, array
	move $a1, $s0
	jal Sort
	
	li $t0, 0
	
print_loop:
	beq $t0, $s0, end_program
	
	la $t1, array
	sll $t2, $t0, 2
	add $t1, $t1, $t2
	lw $a0, 0($t1)
	
	li $v0, 1
	syscall
	
	addi $t0, $t0, 1
	
	beq $t0, $s0, print_done
	
	li $v0, 4
	la $a0, comma
	syscall 
	j print_loop

print_done:
	li $v0, 4
	la $a0, newline
	syscall
	
end_program:
	li $v0, 10
	syscall
	
Sort:
	li $t0, 0

outer_loop:
	addi $t7, $a1, -1
	bge $t0, $t7, sort_done
	
	li $t1, 0
	
	inner_loop:
		sub $t6, $a1, $t0
		addi $t6, $t6, -1
		
		bge $t1, $t6, next_outer
		
		sll $t2, $t1, 2
		add $t3, $a0, $t2
		
		lw $t4, 0($t3)
		lw $t5, 4($t3)
		
		ble $t4, $t5, no_swap
		
		sw $t5, 0($t3)
		sw $t4, 4($t3)
		

no_swap:
	addi $t1, $t1, 1
	j inner_loop

next_outer:
	addi $t0, $t0, 1
	j outer_loop
	
sort_done:
	jr $ra
	
