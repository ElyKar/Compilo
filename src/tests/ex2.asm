	; entete
	extrn lirent:proc, ecrent:proc
	extrn ecrbool:proc
	extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

; trace:
trace:

	; ouvbloc 0
	enter 0,0

	; iload 6
	push word ptr [bp+6]

	; iload 4
	push word ptr [bp+4]

	; idiff
	pop bx
	pop ax
	cmp ax, bx
	je $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux SINON1
	pop ax
	cmp ax, 0
	je SINON1

	; iload 4
	push word ptr [bp+4]

	; iconst 25
	push word ptr 25

	; iload 6
	push word ptr [bp+6]

	; isub
	pop bx
	pop ax
	sub ax, bx
	push ax

	; iegal
	pop bx
	pop ax
	cmp ax, bx
	jne $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux SINON2
	pop ax
	cmp ax, 0
	je SINON2

	; iconst 0
	push word ptr 0

	; ecrireEnt
	; call ecrent
	call ecrent


	; goto FSI2
	jmp FSI2

; SINON2:
SINON2:

	; iconst 1
	push word ptr 1

	; ecrireEnt
	; call ecrent
	call ecrent


; FSI2:
FSI2:

	; goto FSI1
	jmp FSI1

; SINON1:
SINON1:

	; iconst 0
	push word ptr 0

	; ecrireEnt
	; call ecrent
	call ecrent


; FSI1:
FSI1:

	; iload 6
	push word ptr [bp+6]

	; iconst 24
	push word ptr 24

	; iegal
	pop bx
	pop ax
	cmp ax, bx
	jne $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux SINON3
	pop ax
	cmp ax, 0
	je SINON3

	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; goto FSI3
	jmp FSI3

; SINON3:
SINON3:

; FSI3:
FSI3:

	; iconst -1
	push word ptr -1

	; ireturn 8
	pop ax
	mov word ptr [bp+8], ax

	; fermeBloc 4
	leave
	ret 4

; main:
debut :

	STARTUPCODE

main:

	; ouvbloc 6
	enter 6,0

	; iconst 0
	push word ptr 0

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; iconst 0
	push word ptr 0

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

; FAIRE1:
FAIRE1:

	; iload -4
	push word ptr [bp-4]

	; iconst 1
	push word ptr 1

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; iconst 25
	push word ptr 25

	; idiff
	pop bx
	pop ax
	cmp ax, bx
	je $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT1
	pop ax
	cmp ax, 0
	je FAIT1

	; ecrireChaine                             
.DATA
	mess0 DB "                            $"
.CODE
	lea dx, mess0
	push dx
	; call ecrch
	call ecrch


; FAIRE2:
FAIRE2:

	; iload -2
	push word ptr [bp-2]

	; iconst 1
	push word ptr 1

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; iconst 25
	push word ptr 25

	; idiff
	pop bx
	pop ax
	cmp ax, bx
	je $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT2
	pop ax
	cmp ax, 0
	je FAIT2

	; reserveRetour
	sub sp, 2

	; iload -2
	push word ptr [bp-2]

	; iconst 1
	push word ptr 1

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; iload -4
	push word ptr [bp-4]

	; iconst 1
	push word ptr 1

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; call trace
	call trace

	; istore -6
	pop ax
	mov word ptr [bp-6], ax

	; iload -2
	push word ptr [bp-2]

	; iconst 1
	push word ptr 1

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; goto FAIRE2
	jmp FAIRE2

; FAIT2:
FAIT2:

	; iload -4
	push word ptr [bp-4]

	; iconst 1
	push word ptr 1

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

	; iconst 0
	push word ptr 0

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; goto FAIRE1
	jmp FAIRE1

; FAIT1:
FAIT1:

	; queue
	nop
	EXITCODE
	end debut

