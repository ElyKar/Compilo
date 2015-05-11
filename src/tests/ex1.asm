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

	; ouvbloc 10
	enter 10,0

	; iconst 10
	push word ptr 10

	; iconst 20
	push word ptr 20

	; iconst 2
	push word ptr 2

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; iconst 5
	push word ptr 5

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; iload -2
	push word ptr [bp-2]

	; ecrireEnt
	; call ecrent
	call ecrent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; iload -2
	push word ptr [bp-2]

	; iconst 3
	push word ptr 3

	; iload -2
	push word ptr [bp-2]

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

	; iconst 10
	push word ptr 10

	; isub
	pop bx
	pop ax
	sub ax, bx
	push ax

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

	; iload -4
	push word ptr [bp-4]

	; ecrireEnt
	; call ecrent
	call ecrent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; iconst 2
	push word ptr 2

	; ineg
	pop bx
	mov ax, 0
	sub ax, bx
	push ax

	; iconst 3
	push word ptr 3

	; ineg
	pop bx
	mov ax, 0
	sub ax, bx
	push ax

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; ineg
	pop bx
	mov ax, 0
	sub ax, bx
	push ax

	; iconst 7
	push word ptr 7

	; ineg
	pop bx
	mov ax, 0
	sub ax, bx
	push ax

	; isub
	pop bx
	pop ax
	sub ax, bx
	push ax

	; ecrireEnt
	; call ecrent
	call ecrent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; ecrireChaine ce programme a du ecrire 4 puis 6 puis 1
.DATA
	mess0 DB "ce programme a du ecrire 4 puis 6 puis 1$"
.CODE
	lea dx, mess0
	push dx
	; call ecrch
	call ecrch


	; queue
	nop
	EXITCODE
	end debut

