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

	; iconst -1
	push word ptr -1

	; istore -6
	pop ax
	mov word ptr [bp-6], ax

	; iconst 2
	push word ptr 2

	; iload -2
	push word ptr [bp-2]

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; iload -6
	push word ptr [bp-6]

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; iconst 3
	push word ptr 3

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

	; queue
	nop
	EXITCODE
	end debut

