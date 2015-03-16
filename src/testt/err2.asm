	; entete
	extrn lirent:proc, ecrent:proc
	extrn ecrbool:proc
	extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

; main:
debut :

	STARTUPCODE

main:

	; ouvbloc 6
	enter 6,0

	; iconst 2
	push word ptr 2

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; iload -2
	push word ptr [bp-2]

	; iconst 4
	push word ptr 4

	; iinf
	pop bx
	pop ax
	cmp ax, bx
	jge $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; ecrireBool
	; call ecrbool
	call ecrbool


	; iconst 2
	push word ptr 2

	; iload -2
	push word ptr [bp-2]

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; iconst 3
	push word ptr 3

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; iconst 3
	push word ptr 3

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; queue
	nop
	EXITCODE
	end debut

