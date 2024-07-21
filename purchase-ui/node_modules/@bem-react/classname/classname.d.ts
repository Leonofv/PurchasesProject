/**
 * List of classname.
 */
export type ClassNameList = string | Array<string | undefined>;
/**
 * Allowed modifiers format.
 *
 * @see https://en.bem.info/methodology/key-concepts/#modifier
 */
export type NoStrictEntityMods = Record<string, string | boolean | number | undefined>;
/**
 * BEM Entity className initializer.
 */
export type ClassNameInitilizer = (blockName: string, elemName?: string) => ClassNameFormatter;
/**
 * BEM Entity className formatter.
 */
export interface ClassNameFormatter {
    (): string;
    (mods?: NoStrictEntityMods | null, mix?: ClassNameList): string;
    (elemName: string, elemMix?: ClassNameList): string;
    (elemName: string, elemMods?: NoStrictEntityMods | null, elemMix?: ClassNameList): string;
}
/**
 * Settings for the naming convention.
 */
export type Preset = {
    /**
     * Global namespace.
     *
     * @example `omg-Bem-Elem_mod_val`
     */
    n?: string;
    /**
     * Elem's delimiter.
     */
    e?: string;
    /**
     * Modifier's delimiter.
     */
    m?: string;
    /**
     * Modifier value delimiter.
     */
    v?: string;
};
/**
 * BEM className configure function.
 *
 * @example
 * ``` ts
 *
 * import { withNaming } from '@bem-react/classname';
 *
 * const cn = withNaming({ n: 'ns-', e: '__', m: '_' });
 *
 * cn('block', 'elem'); // 'ns-block__elem'
 * ```
 *
 * @param preset settings for the naming convention
 */
export declare function withNaming(preset: Preset): ClassNameInitilizer;
/**
 * BEM Entity className initializer with React naming preset.
 *
 * @example
 * ``` ts
 *
 * import { cn } from '@bem-react/classname';
 *
 * const cat = cn('Cat');
 *
 * cat(); // Cat
 * cat({ size: 'm' }); // Cat_size_m
 * cat('Tail'); // Cat-Tail
 * cat('Tail', { length: 'small' }); // Cat-Tail_length_small
 *
 * const dogPaw = cn('Dog', 'Paw');
 *
 * dogPaw(); // Dog-Paw
 * dogPaw({ color: 'black', exists: true }); // Dog-Paw_color_black Dog-Paw_exists
 * ```
 *
 * @see https://en.bem.info/methodology/naming-convention/#react-style
 */
export declare const cn: ClassNameInitilizer;
