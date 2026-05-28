.data
    result_msg: .asciiz "Result: "

.text
    main:
        # Load two fixed values into registers
        li $t0, -2147483640     # first number
        li $t1, 2      # second number

        # Add them
        subu $t2, $t0, $t1

        # Print message "Result: "
        li $v0, 4
        la $a0, result_msg
        syscall

        # Print integer result
        li $v0, 1
        move $a0, $t2
        syscall

        # Exit
        li $v0, 10
        syscall