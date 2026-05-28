.data
    message: .asciiz "Hello, World!"

.text
    main:
        # Load address of the string into $a0
        la $a0, message

        # System call 4 = print string
        li $v0, 4
        syscall

        # Exit program
        li $v0, 10
        syscall