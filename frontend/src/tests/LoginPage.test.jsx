import {describe, expect, test} from 'vitest';
import {render, screen} from '@testing-library/react';
import LoginPage from '../Pages/LoginPage';

describe("LoginPage test", () => {
    test("should print 'Welcome back!' sign", () => {
        
        render(<LoginPage/>);

        expect(screen.getByText(/Welcome back!/i)).toBeDefined()
    })
})