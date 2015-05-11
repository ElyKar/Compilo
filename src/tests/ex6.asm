	; entete
	extrn lirent:proc, ecrent:proc
	extrn ecrbool:proc
	extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

; somdiv:
somdiv:

	; ouvbloc 4
	enter 4,0

	; iconst 1
	push word ptr 1

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

	; iconst 2
	push word ptr 2

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

; FAIRE1:
FAIRE1:

	; iload -2
	push word ptr [bp-2]

	; iload 4
	push word ptr [bp+4]

	; iconst 2
	push word ptr 2

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

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

	; iload 4
	push word ptr [bp+4]

	; iload -2
	push word ptr [bp-2]

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; iload -2
	push word ptr [bp-2]

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; iload 4
	push word ptr [bp+4]

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

	; iload -4
	push word ptr [bp-4]

	; iload -2
	push word ptr [bp-2]

	; iadd
	pop bx
	pop ax
	add ax, bx
	push ax

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

	; goto FSI1
	jmp FSI1

; SINON1:
SINON1:

; FSI1:
FSI1:

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

	; iload -4
	push word ptr [bp-4]

	; ireturn 6
	pop ax
	mov word ptr [bp+6], ax

	; fermeBloc 2
	leave
	ret 2

; main:
debut :

	STARTUPCODE

main:

	; ouvbloc 10
	enter 10,0

	; iconst 1
	push word ptr 1

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

; FAIRE2:
FAIRE2:

	; iload -2
	push word ptr [bp-2]

	; iconst 300
	push word ptr 300

	; iinfegal
	pop bx
	pop ax
	cmp ax, bx
	jg $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT2
	pop ax
	cmp ax, 0
	je FAIT2

	; iload -2
	push word ptr [bp-2]

	; istore -4
	pop ax
	mov word ptr [bp-4], ax

; FAIRE3:
FAIRE3:

	; iload -4
	push word ptr [bp-4]

	; iconst 300
	push word ptr 300

	; iinfegal
	pop bx
	pop ax
	cmp ax, bx
	jg $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iffaux FAIT3
	pop ax
	cmp ax, 0
	je FAIT3

	; reserveRetour
	sub sp, 2

	; iload -2
	push word ptr [bp-2]

	; call somdiv
	call somdiv

	; istore -6
	pop ax
	mov word ptr [bp-6], ax

	; reserveRetour
	sub sp, 2

	; iload -4
	push word ptr [bp-4]

	; call somdiv
	call somdiv

	; istore -8
	pop ax
	mov word ptr [bp-8], ax

	; iload -6
	push word ptr [bp-6]

	; iload -4
	push word ptr [bp-4]

	; iegal
	pop bx
	pop ax
	cmp ax, bx
	jne $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iload -8
	push word ptr [bp-8]

	; iload -2
	push word ptr [bp-2]

	; iegal
	pop bx
	pop ax
	cmp ax, bx
	jne $+6
	push word ptr -1
	jmp $+4
	push word ptr 0

	; iand
	pop bx
	pop ax
	and ax, bx
	push ax

	; iffaux SINON2
	pop ax
	cmp ax, 0
	je SINON2

	; iload -2
	push word ptr [bp-2]

	; ecrireEnt
	; call ecrent
	call ecrent


	; ecrireChaine --
.DATA
	mess0 DB "--$"
.CODE
	lea dx, mess0
	push dx
	; call ecrch
	call ecrch


	; iload -4
	push word ptr [bp-4]

	; ecrireEnt
	; call ecrent
	call ecrent


	; aLaLigne
	; call ligsuiv
	call ligsuiv


	; lireEnt -10
	lea dx, [bp-10]
	push dx
	; call lirent
	call lirent


	; goto FSI2
	jmp FSI2

; SINON2:
SINON2:

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

	; goto FAIRE3
	jmp FAIRE3

; FAIT3:
FAIT3:

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

	; queue
	nop
	EXITCODE
	end debut

