	; entete
	extrn lirent:proc, ecrent:proc
	extrn ecrbool:proc
	extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

; dessin:
dessin:

	; ouvbloc 12
	enter 12,0

	; iconst 0
	push word ptr 0

	; istore -8
	pop ax
	mov word ptr [bp-8], ax

	; iload 4
	push word ptr [bp+4]

	; iconst -1
	push word ptr -1

	; iegal
	pop bx
	pop ax
	cmp ax, bx
	jne $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux SINON1
	pop ax
	cmp ax, 0
	je SINON1

	; iconst 0
	push word ptr 0

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; goto FSI1
	jmp FSI1

; SINON1:
SINON1:

	; iload 6
	push word ptr [bp+6]

	; iconst 2
	push word ptr 2

	; isub
	pop bx
	pop ax
	sub ax, bx
	push ax

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

; FSI1:
FSI1:

; FAIRE1:
FAIRE1:

	; iload -2
	push word ptr [bp-2]

	; iload 6
	push word ptr [bp+6]

	; iinfegal
	pop bx
	pop ax
	cmp ax, bx
	jg $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT1
	pop ax
	cmp ax, 0
	je FAIT1

	; iconst 0
	push word ptr 0

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

; FAIRE2:
FAIRE2:

	; iload -4
	push word ptr [bp-4]

	; iload -2
	push word ptr [bp-2]

	; iinf
	pop bx
	pop ax
	cmp ax, bx
	jge $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT2
	pop ax
	cmp ax, 0
	je FAIT2

	; iload -4
	push word ptr [bp-4]

	; iload -4
	push word ptr [bp-4]

	; iconst 2
	push word ptr 2

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; iconst 2
	push word ptr 2

	; imul
	pop bx
	pop ax
	imul bx
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

	; ecrireChaine *
.DATA
	mess0 DB "*$"
.CODE
	lea dx, mess0
	push dx
	; call ecrch
	call ecrch


	; goto FSI2
	jmp FSI2

; SINON2:
SINON2:

	; ecrireChaine  
.DATA
	mess1 DB " $"
.CODE
	lea dx, mess1
	push dx
	; call ecrch
	call ecrch


; FSI2:
FSI2:

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

	; goto FAIRE2
	jmp FAIRE2

; FAIT2:
FAIT2:

	; iload -2
	push word ptr [bp-2]

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

; FAIRE3:
FAIRE3:

	; iload -4
	push word ptr [bp-4]

	; iload 6
	push word ptr [bp+6]

	; iinf
	pop bx
	pop ax
	cmp ax, bx
	jge $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT3
	pop ax
	cmp ax, 0
	je FAIT3

	; iload -4
	push word ptr [bp-4]

	; iload -4
	push word ptr [bp-4]

	; iconst 3
	push word ptr 3

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; iconst 3
	push word ptr 3

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; idiff
	pop bx
	pop ax
	cmp ax, bx
	je $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux SINON3
	pop ax
	cmp ax, 0
	je SINON3

	; ecrireChaine -
.DATA
	mess2 DB "-$"
.CODE
	lea dx, mess2
	push dx
	; call ecrch
	call ecrch


	; goto FSI3
	jmp FSI3

; SINON3:
SINON3:

	; ecrireChaine  
.DATA
	mess3 DB " $"
.CODE
	lea dx, mess3
	push dx
	; call ecrch
	call ecrch


; FSI3:
FSI3:

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

	; goto FAIRE3
	jmp FAIRE3

; FAIT3:
FAIT3:

	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; iload -8
	push word ptr [bp-8]

	; iload -2
	push word ptr [bp-2]

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; istore -8
	pop ax
	mov word ptr [bp-8], ax

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

	; goto FAIRE1
	jmp FAIRE1

; FAIT1:
FAIT1:

	; iload -8
	push word ptr [bp-8]

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

	; ouvbloc 4
	enter 4,0

	; lireEnt -4
	lea dx, [bp-4]
	push dx
	; call lirent
	call lirent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; reserveRetour
	sub sp, 2

	; iload -4
	push word ptr [bp-4]

	; iconst -1
	push word ptr -1

	; call dessin
	call dessin

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; iload -2
	push word ptr [bp-2]

	; ecrireEnt
	; call ecrent
	call ecrent


	; queue
	nop
	EXITCODE
	end debut

