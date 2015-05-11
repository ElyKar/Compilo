	; entete
	extrn lirent:proc, ecrent:proc
	extrn ecrbool:proc
	extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

; max:
max:

	; ouvbloc 4
	enter 4,0

	; lireEnt -2
	lea dx, [bp-2]
	push dx
	; call lirent
	call lirent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; lireEnt -4
	lea dx, [bp-4]
	push dx
	; call lirent
	call lirent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; iload -2
	push word ptr [bp-2]

	; iload -4
	push word ptr [bp-4]

	; isup
	pop bx
	pop ax
	cmp ax, bx
	jle $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux SINON1
	pop ax
	cmp ax, 0
	je SINON1

	; iload -2
	push word ptr [bp-2]

	; ireturn 4
	pop ax
	mov word ptr [bp+4], ax

	; goto FSI1
	jmp FSI1

; SINON1:
SINON1:

	; iload -4
	push word ptr [bp-4]

	; ireturn 4
	pop ax
	mov word ptr [bp+4], ax

; FSI1:
FSI1:

	; fermeBloc 0
	leave
	ret 0

; main:
debut :

	STARTUPCODE

main:

	; ouvbloc 4
	enter 4,0

	; reserveRetour
	sub sp, 2

	; call max
	call max

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; iload -2
	push word ptr [bp-2]

	; ecrireEnt
	; call ecrent
	call ecrent


	; queue
	nop
	EXITCODE
	end debut

